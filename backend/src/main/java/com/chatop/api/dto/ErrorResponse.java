package com.chatop.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for error responses
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
}
