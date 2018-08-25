package com.example.wordchain.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordChain {
    private final long id;
    private final String startWord;
    private final String endWord;
    private final String[] chain;

    public WordChain() {
        this.id = -1;
        this.startWord = "";
        this.endWord = "";
        this.chain = null;
    }

    public WordChain(long id, String startWord, String endWord, String[] chain) {
        this.id = id;
        this.startWord = startWord;
        this.endWord = endWord;
        this.chain = chain;
    }
}
