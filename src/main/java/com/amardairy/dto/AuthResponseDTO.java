package com.amardairy.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDTO {
    private Long userId;
    private String name;
    private String phone;
    private String address;
    private String token;
    private String message;
}