package com.example.inventory_management_api.controller;

import com.example.inventory_management_api.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setUsername("integrationTestUser");
        user.setEmail("integration@example.com");
        user.setPassword("password");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("integrationTestUser"));
    }

    @Test
    public void testGetUserById() throws Exception {
        mockMvc.perform(get("/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setUsername("updatedUser");

        mockMvc.perform(put("/users/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("updatedUser"));
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/users/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAllUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    public void testInvalidUserId() throws Exception {
        mockMvc.perform(get("/users/{id}", 999))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateUserWithInvalidEmail() throws Exception {
        User user = new User();
        user.setUsername("testUser");
        user.setEmail("invalidemail");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateUserWithMissingUsername() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetNonExistingUser() throws Exception {
        mockMvc.perform(get("/users/{id}", 999))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteNonExistingUser() throws Exception {
        mockMvc.perform(delete("/users/{id}", 999))
                .andExpect(status().isNotFound());
    }
}
