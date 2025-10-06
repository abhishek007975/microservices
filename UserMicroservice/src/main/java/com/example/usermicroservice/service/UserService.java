package com.example.usermicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.usermicroservice.entity.User;
import com.example.usermicroservice.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "Email already registered!";
        }
        userRepository.save(user);
        return "User registered successfully!";
    }

    public String loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword() != null
            && user.getPassword().trim().equals(password.trim())) {
            return "Login successful!";
        }
        return "Invalid credentials!";
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
