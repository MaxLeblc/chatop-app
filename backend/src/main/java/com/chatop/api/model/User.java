package com.chatop.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a User
 * 
 * Table: USERS
 * Fields: id, email, name, password, created_at, updated_at
 */
@Entity
@Table(name = "USERS")
public class User {
    
    // TODO: Add your fields and annotations here
    // Hint: Use @Id, @GeneratedValue, @Column, @CreatedDate, @UpdatedDate
    
}
