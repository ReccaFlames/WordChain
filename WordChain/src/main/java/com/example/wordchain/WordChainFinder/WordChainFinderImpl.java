package com.example.wordchain.WordChainFinder;

import com.example.wordchain.WordChainFinder.DictionaryLoader.DictionaryFileLoader;

import java.util.*;

public class WordChainFinderImpl implements WordChainFinder {

    @Override
    public List<String> findWordChain(String startWord, String endWord) {
        DictionaryFileLoader dictLoader = new DictionaryFileLoader("/static/wordlist.txt");
        Set<String> dict = dictLoader.getDictionaryForWord(startWord.length());

        return (List<String>) searchByBreadthAlgorythm(startWord, endWord, dict);
    }

    private Deque<String> searchByBreadthAlgorythm(String startWord, String endWord, Set<String> dict) {
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
}
