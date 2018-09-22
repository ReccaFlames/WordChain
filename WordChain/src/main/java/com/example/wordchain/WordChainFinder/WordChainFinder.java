package com.example.wordchain.WordChainFinder;

import java.util.Deque;

public interface WordChainFinder {
    Deque<String> findWordChain(String startWord, String endWord);
}
