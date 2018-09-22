package com.example.wordchain.WordChainFinder;

import com.example.wordchain.WordChainFinder.DictionaryLoader.DictionaryFileLoader;

import java.util.*;
import java.util.stream.Collector;

public class WordChainFinderImpl implements WordChainFinder {

    @Override
    public Deque<String> findWordChain(String startWord, String endWord) {
        ensureWordIsNotEmpty(startWord);
        ensureWordIsNotEmpty(endWord);
        ensureInput(startWord, endWord);
        startWord = startWord.trim().toLowerCase();
        endWord = endWord.trim().toLowerCase();

        DictionaryFileLoader dictLoader = new DictionaryFileLoader("/static/wordlist.txt");
        Set<String> dict = dictLoader.getDictionaryForWord(startWord.length());

        if (containsWordsInDictionary(startWord, endWord, dict)) {
            return bidirectionalSearchAlgorithm(startWord, endWord, dict);
        }

        return new LinkedList<>();
    }

    private Boolean containsWordsInDictionary(String first, String second, Set<String> dict) {
        return dict.contains(first) && dict.contains(second);
    }

    private Deque<String> bidirectionalSearchAlgorithm(String startWord, String endWord, Set<String> dict) {
        Queue<String> beginQueue = new LinkedList<>();
        Queue<String> endQueue = new LinkedList<>();
        Map<String, String> beginParents = new HashMap<>();
        Map<String, String> endParents = new HashMap<>();

        beginQueue.add(startWord);
        endQueue.add(endWord);

        Set<String> visitedBegin = new HashSet<>();
        Set<String> visitedEnd = new HashSet<>();

        while (!beginQueue.isEmpty() || !endQueue.isEmpty()) {
            String currentNode = beginQueue.poll();

            if (endQueue.contains(currentNode)) {
                return getPath(endParents, beginParents, currentNode);
            }

            visitedBegin.add(currentNode);

            getChildren(currentNode, dict).stream().filter(children -> !visitedBegin.contains(children)).forEach(children -> {
                beginQueue.add(children);
                beginParents.putIfAbsent(children, currentNode);
            });

            String currentNode2 = endQueue.poll();

            visitedEnd.add(currentNode2);

            getChildren(currentNode2, dict).stream().filter(children -> !visitedEnd.contains(children)).forEach(children -> {
                endQueue.add(children);
                endParents.putIfAbsent(children, currentNode2);
            });

        }

        return new LinkedList<>();
    }

    private Deque<String> getPath(Map<String, String> endParents, Map<String, String> beginParents, String currentNode) {
        Deque<String> endDeque = backtrack(currentNode, endParents);
        endDeque.removeFirst();
        Deque<String> startDeque = backtrack(currentNode, beginParents);
        endDeque.stream().forEach(element -> startDeque.addFirst(element));

        return startDeque.stream().collect(Collector.of(ArrayDeque::new, (deq, t) -> deq.addFirst(t), (d1, d2) -> {
            d2.addAll(d1);
            return d2;
        }));
    }

    private Deque<String> backtrack(String lastNode, Map<String, String> parents) {
        Deque<String> path = new LinkedList<>();

        String currentNode = lastNode;
        do {
            path.add(currentNode);
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
        if (word == null || word.equals("") || word.length() == 0) {
            throw new IllegalArgumentException("Input word(s) cannot be null or empty");
        }
    }

    private void ensureIsAlpha(String word) {
        char[] chars = word.toCharArray();

        for (char c : chars) {
            if (!Character.isLetter(c)) {
                throw new IllegalArgumentException("Words can contain only letters");
            }
        }

    }
}
