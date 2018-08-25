package com.example.wordchain.Controller;

import com.example.wordchain.Model.WordChain;
import com.example.wordchain.WordChainFinder.WordChainFinderImpl;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class WordChainService {

    private final AtomicLong counter = new AtomicLong();

    public WordChain findWordChain(String startWord, String endWord) {
        WordChainFinderImpl wordChainFinder = new WordChainFinderImpl();
        String[] chain = wordChainFinder.findWordChain(startWord, endWord).toArray(new String[0]);

        return new WordChain(counter.incrementAndGet(), startWord, endWord, chain);
    }

}
