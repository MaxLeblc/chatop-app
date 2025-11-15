package com.chatop.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for Message creation
 */
@Data
public class MessageRequest {

    @NotNull(message = "Rental ID is required")
    private Integer rentalId;

    @NotNull(message = "User ID is required")
    private Integer userId;

    @NotBlank(message = "Message is required")
    @Size(max = 2000, message = "Message must not exceed 2000 characters")
    private String message;
}
