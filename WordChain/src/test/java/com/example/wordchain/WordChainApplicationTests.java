package com.example.wordchain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WordChainApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WordChainController controller;

    @MockBean
    private WordChainService service;

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
    public void wordChainWhenParamsCorrectShouldReturnResultFromService() throws Exception{
        String startWord = "cat";
        String endWord = "dog";
        when(service.findWordChain(startWord,endWord)).thenReturn("cat -> cot -> cog -> dog");

        this.mockMvc.perform(get("/wordChain")
                .param("startWord","cat")
                .param("endWord","dog"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("cat -> cot -> cog -> dog")));
    }

    @Test
    public void wordChainWhenParamsEmptyShouldReturnResultFromService() throws Exception{
        String startWord = "";
        String endWord = "";

        this.mockMvc.perform(get("/wordChain")
                .param("startWord",startWord)
                .param("endWord",endWord))
                .andExpect(status().isOk());
    }
}
