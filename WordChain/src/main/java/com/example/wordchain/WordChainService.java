package com.example.wordchain;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WordChainService {
    public String greet() {
        return "Hello World";
    }

    public String findWordChain(String startWord, String endWord) {
        DictionaryFileLoader dictionaryFileLoader = new DictionaryFileLoader("/static/wordlist.txt");
        Set<String> dict = dictionaryFileLoader.getDictionary(startWord.length());

        String chain = null;

        WordChainAlgorithmImpl wci = new WordChainAlgorithmImpl();
        try {
            chain = wci.getWordChains(startWord, endWord,dict)
                    .stream()
                    .collect(Collectors.joining(" -> "));
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
        System.out.println("TEST "+startWord+" "+endWord);
        String result = "THE RESULT IS: \n"+(chain.isEmpty() ? "No word chain found" : chain);

        return result;
    }
}
