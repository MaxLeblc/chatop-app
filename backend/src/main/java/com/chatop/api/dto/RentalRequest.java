package com.chatop.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for Rental creation/update
 */
@Data
public class RentalRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    @NotNull(message = "Surface is required")
    @Positive(message = "Surface must be positive")
    private BigDecimal surface;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;

    private String picture;

    @Size(max = 2000, message = "Description must not exceed 2000 characters")
    private String description;
}
