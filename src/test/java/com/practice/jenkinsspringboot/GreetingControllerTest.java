package com.practice.jenkinsspringboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void homeEndpointReturnsWelcomeMessage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Spring Boot project is running!"));
    }

    @Test
    void greetingEndpointUsesProvidedName() throws Exception {
        mockMvc.perform(get("/api/greet").param("name", "Sanchit"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "Hello, Sanchit! Welcome to Jenkins Pipeline Practice."));
    }

    @Test
    void greetingEndpointUsesDefaultName() throws Exception {
        mockMvc.perform(get("/api/greet"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "Hello, Developer! Welcome to Jenkins Pipeline Practice."));
    }
}
