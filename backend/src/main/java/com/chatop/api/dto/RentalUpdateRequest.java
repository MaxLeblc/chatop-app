package com.chatop.api.dto;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for Rental update (PUT)
 * All fields are optional
 */
@Data
public class RentalUpdateRequest {

    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    @Positive(message = "Surface must be positive")
    private BigDecimal surface;

    @Positive(message = "Price must be positive")
    private BigDecimal price;

    private MultipartFile picture;

    @Size(max = 2000, message = "Description must not exceed 2000 characters")
    private String description;
}