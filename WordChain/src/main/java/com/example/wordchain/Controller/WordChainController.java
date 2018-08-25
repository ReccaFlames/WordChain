package com.example.wordchain.Controller;

import com.example.wordchain.Model.WordChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class WordChainController {

    private final WordChainService service;
    @Autowired
    JdbcTemplate jdbcTemplate;

    public WordChainController(WordChainService service) {
        this.service = service;
    }

    @RequestMapping(value = "/wordChain", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity getWordChain(@RequestParam String startWord, @RequestParam String endWord) {
        WordChain wordChain = service.findWordChain(startWord, endWord);
        jdbcTemplate.update("insert into results (start_word, end_word, path) " + "values(?, ?, ?)",
                new Object[]{
                        wordChain.getStartWord(), wordChain.getEndWord(),
                        Arrays.stream(wordChain.getChain()).collect(Collectors.joining(","))
                });
        return ResponseEntity.ok(wordChain);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ResponseEntity handleException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
