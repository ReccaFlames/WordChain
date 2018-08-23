package com.example.wordchain;

import com.example.wordchain.WordChainFinder.WordChainFinderImpl;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class WordChainService {
    public String greet() {
        return "Hello World";
    }

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
        String result = "THE RESULT IS: \n" + (chain.isEmpty() ? "No word chain found" : chain);

        return result;
    }

}
