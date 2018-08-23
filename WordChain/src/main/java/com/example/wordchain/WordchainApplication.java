package com.example.wordchain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@SpringBootApplication
public class WordchainApplication {

//    @RequestMapping(value="/sayHello", method = RequestMethod.GET)
//    public String sayHello(){
//        String startWord = "ruby";
//        String endWord = "code";
//
//        DictionaryFileLoader dictionaryFileLoader = new DictionaryFileLoader("/static/wordlist.txt");
//        Set<String> dict = dictionaryFileLoader.getDictionary(startWord.length());
//
//        String chain = null;
//
//        WordChainAlgorithmImpl wci = new WordChainAlgorithmImpl();
//        try {
//            chain = wci.getWordChains(startWord, endWord,dict)
//                    .stream()
//                    .collect(Collectors.joining(" -> "));
//        } catch (IllegalArgumentException e) {
//            System.out.println("[ERROR] " + e.getMessage());
//        }
//
//        String result = "THE RESULT IS: \n"+(chain.isEmpty() ? "No word chain found" : chain);
//
//        return result;
//    }

    public static void main(String[] args) {
        SpringApplication.run(WordchainApplication.class, args);
    }
}
