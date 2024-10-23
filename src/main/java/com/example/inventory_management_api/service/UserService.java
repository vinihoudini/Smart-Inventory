package com.example.inventory_management_api.service;

import com.example.inventory_management_api.dto.AuthRequest;
import com.example.inventory_management_api.dto.ChangePasswordRequest;
import com.example.inventory_management_api.exception.InvalidDataException;
import com.example.inventory_management_api.exception.UserNotFoundException;
import com.example.inventory_management_api.model.User;
import com.example.inventory_management_api.repository.UserRepository;
import com.example.inventory_management_api.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String authenticate(AuthRequest authRequest) {
        User user = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado: " + authRequest.getUsername()));

        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new InvalidDataException("Senha incorreta");
        }

        return jwtUtil.generateToken(user.getUsername());
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário com ID " + id + " não encontrado"));
    }

    public void changePassword(ChangePasswordRequest request) {
        // Lógica para mudar a senha
    }

    public User create(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new InvalidDataException("Nome de usuário é obrigatório");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário com ID " + id + " não encontrado"));
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        return userRepository.save(existingUser);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Usuário com ID " + id + " não encontrado");
        }
        userRepository.deleteById(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
