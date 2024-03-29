package com.example.wordchain.WordChainFinder.DictionaryLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class DictionaryFileLoader implements DictionaryLoader {

    private final File file;

    public DictionaryFileLoader(String fileName) {
        URL fileUrl = getClass().getResource(fileName);
        this.file = new File(fileUrl.getFile());
    }


    @Override
    public Set<String> getDictionaryForWord(Integer wordLength) {
        if (file == null) {
            return null;
        }

        Set<String> dict = new TreeSet<>();
        try (BufferedReader br = Files.newBufferedReader(file.toPath(), StandardCharsets.ISO_8859_1)) {
            dict = br.lines().filter(line -> line.length() == wordLength).map(String::toLowerCase).map(String::trim).collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dict;
    }
}
