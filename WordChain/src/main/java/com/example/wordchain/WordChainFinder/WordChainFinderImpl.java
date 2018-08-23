package com.example.wordchain.WordChainFinder;

import com.example.wordchain.WordChainFinder.DictionaryLoader.DictionaryFileLoader;

import java.util.*;

public class WordChainFinderImpl implements WordChainFinder {

    @Override
    public List<String> findWordChain(String startWord, String endWord) {
        ensureInput(startWord, endWord);

        DictionaryFileLoader dictLoader = new DictionaryFileLoader("/static/wordlist.txt");
        Set<String> dict = dictLoader.getDictionaryForWord(startWord.length());

        if (containsWordsInDictionary(startWord, endWord, dict)) {
            return (List<String>) searchByBreadthAlgorithm(startWord, endWord, dict);
        }

        return new LinkedList<>();
    }

    private Boolean containsWordsInDictionary(String first, String second, Set<String> dict) {
        return dict.contains(first) && dict.contains(second);
    }

    private Deque<String> searchByBreadthAlgorithm(String startWord, String endWord, Set<String> dict) {
        Queue<String> toVisit = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parents = new HashMap<>();

        toVisit.add(startWord);

        while (!toVisit.isEmpty()) {
            String currentNode = toVisit.poll();
            if (currentNode.equals(endWord)) {
                return backtrack(currentNode, parents);
            }

            visited.add(currentNode);

            getChildren(currentNode, dict).stream().filter(children -> !visited.contains(children)).forEach(children -> {
                toVisit.add(children);
                parents.putIfAbsent(children, currentNode);
            });
        }

        return new LinkedList<>();
    }

    private Deque<String> backtrack(String lastNode, Map<String, String> parents) {
        Deque<String> path = new LinkedList<>();

        String currentNode = lastNode;
        do {
            path.push(currentNode);
            currentNode = parents.get(currentNode);
        } while (currentNode != null);

        return path;
    }

    private Set<String> getChildren(String word, Set<String> dict) {
        Set<String> set = new TreeSet<>();
        for (String dictWord : dict) {
            if (wordDifference(word, dictWord) == 1) {
                set.add(dictWord);
            }
        }
        return set;
    }

    private Integer wordDifference(String firstWord, String secondWord) {
        int difference = 0;
        for (int i = 0; i < firstWord.length(); i++)
            if (firstWord.charAt(i) != secondWord.charAt(i)) difference++;
        return difference;
    }

    private void ensureInput(String startWord, String endWord) {
        ensureWordIsNotEmpty(startWord);
        ensureWordIsNotEmpty(endWord);
        ensureSameLength(startWord, endWord);
        ensureIsAlpha(startWord);
        ensureIsAlpha(endWord);
    }

    private void ensureSameLength(String firstWord, String secondWord) {
        if (firstWord.length() != secondWord.length()) {
            throw new IllegalArgumentException("Words must have same length");
        }
    }

    private void ensureWordIsNotEmpty(String word) {
        if (word.equals("") || word.length() == 0) {
            throw new IllegalArgumentException("Input word(s) cannot be null or empty");
        }
    }

    private void ensureIsAlpha(String word) {
        char[] chars = word.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                throw new IllegalArgumentException("Words can contain only letters");
            }
        }

    }
}
