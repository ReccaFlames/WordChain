package com.example.wordchain.WordChainFinder.DictionaryLoader;

import java.util.Set;

public interface DictionaryLoader {
    Set<String> getDictionaryForWord(Integer wordLength);
}
