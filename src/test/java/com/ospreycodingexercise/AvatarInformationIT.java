package com.ospreycodingexercise;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AvatarController.class)
public class AvatarInformationIT 
{
    
    @Autowired
    private MockMvc mockMVC;
    
    @Test
    public void getPlayerLocationIT() throws Exception {
        this.mockMVC.perform(get("/api/board")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location").value("2x2"));
    }

    @Test
    public void getPlayerDirectionIT() throws Exception {
        this.mockMVC.perform(get("/api/board")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.direction").value("NORTH"));
    }
}
