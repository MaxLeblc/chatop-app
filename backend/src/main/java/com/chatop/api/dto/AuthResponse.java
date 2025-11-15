package com.chatop.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO for authentication response (login/register)
 */
@Data
@AllArgsConstructor
public class AuthResponse {

    private String token;
}
