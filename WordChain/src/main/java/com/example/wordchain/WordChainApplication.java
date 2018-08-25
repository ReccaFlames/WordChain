package com.example.wordchain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class WordChainApplication implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(WordChainApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute("DROP TABLE results IF EXISTS ");
        jdbcTemplate.execute("CREATE TABLE results(id SERIAL, start_word VARCHAR(255), end_word VARCHAR(255), path VARCHAR(255))");
    }
}
