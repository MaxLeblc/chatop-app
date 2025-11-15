package com.chatop.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO for generic response message
 */
@Data
@AllArgsConstructor
public class MessageResponse {

    private String message;
}
