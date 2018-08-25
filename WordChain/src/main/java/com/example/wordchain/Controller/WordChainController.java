package com.example.wordchain.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WordChainController {

    private final WordChainService service;

    public WordChainController(WordChainService service) {
        this.service = service;
    }

    @RequestMapping(value = "/wordChain", method = RequestMethod.GET)
    public @ResponseBody
    String getWordChain(@RequestParam String startWord, @RequestParam String endWord) {
        return service.findWordChain(startWord, endWord);
    }
}
