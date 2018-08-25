package com.example.wordchain;

import com.example.wordchain.Controller.WordChainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WordChainApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WordChainController controller;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void wordChainWhenNoParamShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(get("/wordChain"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void wordChainWhenParamsEmptyShouldReturnResultFromService() throws Exception {
        String startWord = "";
        String endWord = "";

        this.mockMvc.perform(get("/wordChain")
                .param("startWord", startWord)
                .param("endWord", endWord))
                .andExpect(status().isOk());
    }
}
