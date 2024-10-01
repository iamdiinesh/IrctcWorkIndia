package com.example.IrctcWorkIndia.service;

import com.example.IrctcWorkIndia.entity.LoginRequest;
import com.example.IrctcWorkIndia.entity.User;
import com.example.IrctcWorkIndia.repository.UserRepository;
import com.example.IrctcWorkIndia.utilities.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Autowired
    UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Object register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            logger.error("Registration attempt failed: User already exists");
            return ResponseHandler.generateResponse(null, "Username already exists", 1);
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        userRepository.save(user);
        logger.info("User {} has been registered successfully.", user.getUsername());

        return ResponseHandler.generateResponse(user, "Registration successful!", 0);
    }


    public Object login(LoginRequest loginRequest) {
        try {
            User user = userRepository.findByUsername(loginRequest.getUsername()).orElse(null);

            if (user == null) {
                logger.error("Login attempt failed: User not found");
                return ResponseHandler.generateResponse(null, "User not found", 1);
            }

            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return ResponseHandler.generateResponse(null, "Password incorrect", 1);
            }

            logger.info("User {} has logged in successfully.", user.getUsername());
            return "Login successful! Welcome, " + user.getUsername();

        } catch (RuntimeException e) {
            logger.error("Login attempt failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error during login: {}", e.getMessage(), e);
            throw new RuntimeException("An unexpected error occurred during login");
        }
    }
}
