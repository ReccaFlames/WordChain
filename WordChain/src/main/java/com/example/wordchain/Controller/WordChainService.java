package com.example.wordchain.Controller;

import com.example.wordchain.WordChainFinder.WordChainFinderImpl;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class WordChainService {

    public String findWordChain(String startWord, String endWord) {
        String chain;

        WordChainFinderImpl wordChainFinder = new WordChainFinderImpl();
        try {
            chain = wordChainFinder.findWordChain(startWord, endWord)
                    .stream()
                    .collect(Collectors.joining(" -> "));
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        String result = (chain.isEmpty() ? "No word chain found" : chain);

        return result;
    }

}
