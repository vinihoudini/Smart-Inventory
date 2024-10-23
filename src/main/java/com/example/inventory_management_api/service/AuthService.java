package com.example.inventory_management_api.service;

import com.example.inventory_management_api.model.User;
import com.example.inventory_management_api.repository.UserRepository;
import com.example.inventory_management_api.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Credenciais inválidas");
        }

        return jwtUtil.generateToken(user);
    }
}

