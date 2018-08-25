package com.example.wordchain.Controller;

import com.example.wordchain.Model.WordChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WordChainController {

    private final WordChainService service;

    public WordChainController(WordChainService service) {
        this.service = service;
    }

    @RequestMapping(value = "/wordChain", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity getWordChain(@RequestParam String startWord, @RequestParam String endWord) {
        WordChain wordChain = service.findWordChain(startWord, endWord);
        return ResponseEntity.ok(wordChain);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ResponseEntity handleException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
