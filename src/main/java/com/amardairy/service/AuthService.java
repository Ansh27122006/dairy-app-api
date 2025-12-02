package com.amardairy.service;

import com.amardairy.dto.AuthResponseDTO;
import com.amardairy.dto.LoginRequestDTO;
import com.amardairy.dto.RegisterRequestDTO;

public interface AuthService {
    AuthResponseDTO register(RegisterRequestDTO request);
    AuthResponseDTO login(LoginRequestDTO request);
}