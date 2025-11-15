package com.chatop.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a Message
 * 
 * Table: MESSAGES
 * Fields: id, rental_id, user_id, message, created_at, updated_at
 */
@Entity
@Table(name = "MESSAGES")
public class Message {
    
    // TODO: Add your fields and annotations here
    // Hint: Use @Id, @GeneratedValue, @Column, @ManyToOne, @JoinColumn
    
}
