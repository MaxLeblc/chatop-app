package com.chatop.api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representing a Rental
 * 
 * Table: RENTALS
 * Fields: id, name, surface, price, picture, description, owner_id, created_at, updated_at
 */
@Entity
@Table(name = "RENTALS")
public class Rental {
    
    // TODO: Add your fields and annotations here
    // Hint: Use @Id, @GeneratedValue, @Column, @ManyToOne, @JoinColumn
    
}
