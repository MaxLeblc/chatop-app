package com.chatop.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * DTO for Rental response
 */
@Data
public class RentalDto {

    private Integer id;
    private String name;
    private BigDecimal surface;
    private BigDecimal price;
    private String picture;
    private String description;
    private Integer ownerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
