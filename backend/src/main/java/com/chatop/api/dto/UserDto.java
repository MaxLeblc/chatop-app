package com.chatop.api.dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * DTO for User information
 */
@Data
public class UserDto {

    private Integer id;
    private String email;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
