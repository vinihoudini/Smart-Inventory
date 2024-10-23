package com.example.inventory_management_api.controller;

import com.example.inventory_management_api.dto.AuthRequest;
import com.example.inventory_management_api.dto.ChangePasswordRequest;
import com.example.inventory_management_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequest authRequest) {
        return userService.authenticate(authRequest);
    }

    @PostMapping("/changePassword")
    public void changePassword(@RequestBody ChangePasswordRequest request) {
        userService.changePassword(request);
    }
}

