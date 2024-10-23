package com.example.inventory_management_api.functional;

import com.example.inventory_management_api.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.jayway.jsonpath.JsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserFunctionalTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUserLifecycle() throws Exception {
        // Step 1: Create a user
        User user = new User();
        user.setUsername("functionalUser");
        user.setEmail("functional@example.com");
        user.setPassword("password");

        // Executar o POST e capturar o resultado
        MvcResult result = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("functionalUser"))
                .andReturn();

        // Extrair o ID do usuário criado
        String responseBody = result.getResponse().getContentAsString();
        Long userId = JsonPath.parse(responseBody).read("$.id", Long.class);

        // Step 2: Retrieve the user by the extracted ID
        mockMvc.perform(get("/users/" + userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("functionalUser"));

        // Step 3: Update the user
        user.setUsername("updatedFunctionalUser");

        mockMvc.perform(put("/users/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("updatedFunctionalUser"));

        // Step 4: Delete the user
        mockMvc.perform(delete("/users/" + userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
