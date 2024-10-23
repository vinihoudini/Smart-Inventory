package com.example.inventory_management_api;

import com.example.inventory_management_api.model.User;
import com.example.inventory_management_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin_password"); // Certifique-se de criptografar a senha!
        userService.save(admin);
    }
}