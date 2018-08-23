package com.example.wordchain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WordChainController.class)
public class WebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WordChainService service;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        when(service.greet()).thenReturn("Hello Mock");
        this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello Mock")));
    }

    @Test
    public void wordChainWhenNoParamShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(get("/wordChain")).andExpect(status().isBadRequest());
    }

    @Test
    public void wordChainWhenParamsCorrectShouldReturnResultFromService() throws Exception{
        String startWord = "cat";
        String endWord = "dog";
        when(service.findWordChain(startWord,endWord)).thenReturn("THE RESULT IS: cat -> cot -> cog -> dog");

        this.mockMvc.perform(get("/wordChain")
                .param("startWord","cat")
                .param("endWord","dog"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("THE RESULT IS: cat -> cot -> cog -> dog")));
    }

}
