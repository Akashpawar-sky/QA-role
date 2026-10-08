package com.example.qa;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateUser() throws Exception {

        String requestBody = """
                {
                    "name": "Automation User",
                    "email": "automation@gmail.com"
                }
                """;

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }
    @Test
    void testInvalidEmail() throws Exception {

        String requestBody = """
                {
                    "name": "Test User",
                    "email": "invalid-email"
                }
                """;

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest());
    }
    @Test
    void testDuplicateEmail() throws Exception {

        String firstUser = """
                {
                    "name": "First User",
                    "email": "duplicate@gmail.com"
                }
                """;

        String duplicateUser = """
                {
                    "name": "Second User",
                    "email": "duplicate@gmail.com"
                }
                """;

        // Create first user
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(firstUser))
                .andExpect(status().isOk());

        // Try same email again
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(duplicateUser))
                .andExpect(status().isBadRequest());
    }
}