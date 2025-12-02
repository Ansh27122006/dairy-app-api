package com.amardairy.serviceImpl;

import com.amardairy.dto.AuthResponseDTO;
import com.amardairy.dto.LoginRequestDTO;
import com.amardairy.dto.RegisterRequestDTO;
import com.amardairy.entity.User;
import com.amardairy.repository.UserRepository;
import com.amardairy.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public AuthResponseDTO register(RegisterRequestDTO request) {
        // Validate phone number
        if (request.getPhone() == null || request.getPhone().length() != 10) {
            throw new RuntimeException("Phone number must be 10 digits");
        }

        // Check if user already exists
        if (userRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Phone number already registered");
        }

        // Validate password
        if (request.getPassword() == null || request.getPassword().length() < 6) {
            throw new RuntimeException("Password must be at least 6 characters");
        }

        // Encrypt password (using Base64 for simplicity - use BCrypt in production)
        String encryptedPassword = Base64.getEncoder().encodeToString(
                request.getPassword().getBytes()
        );

        // Create user
        User user = User.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .password(encryptedPassword)
                .address(request.getAddress())
                .createdAt(LocalDateTime.now())
                .isActive(true)
                .build();

        User savedUser = userRepository.save(user);

        // Generate simple token (userId:phone encoded)
        String token = generateToken(savedUser.getId(), savedUser.getPhone());

        return AuthResponseDTO.builder()
                .userId(savedUser.getId())
                .name(savedUser.getName())
                .phone(savedUser.getPhone())
                .address(savedUser.getAddress())
                .token(token)
                .message("Registration successful")
                .build();
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {
        // Find user by phone
        User user = userRepository.findByPhone(request.getPhone())
                .orElseThrow(() -> new RuntimeException("Invalid phone number or password"));

        // Verify password
        String encryptedPassword = Base64.getEncoder().encodeToString(
                request.getPassword().getBytes()
        );

        if (!user.getPassword().equals(encryptedPassword)) {
            throw new RuntimeException("Invalid phone number or password");
        }

        // Check if user is active
        if (!user.getIsActive()) {
            throw new RuntimeException("Account is inactive");
        }

        // Generate token
        String token = generateToken(user.getId(), user.getPhone());

        return AuthResponseDTO.builder()
                .userId(user.getId())
                .name(user.getName())
                .phone(user.getPhone())
                .address(user.getAddress())
                .token(token)
                .message("Login successful")
                .build();
    }

    private String generateToken(Long userId, String phone) {
        String data = userId + ":" + phone + ":" + System.currentTimeMillis();
        return Base64.getEncoder().encodeToString(data.getBytes());
    }
}