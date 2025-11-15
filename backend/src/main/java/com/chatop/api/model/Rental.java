package com.chatop.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity representing a Rental
 *
 * Table: RENTALS
 * Fields: id, name, surface, price, picture, description, owner_id, created_at,
 * updated_at
 */
@Entity
@Table(name = "RENTALS")
@Getter
@Setter
public class Rental {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 255)
  private String name;

  private BigDecimal surface;

  private BigDecimal price;

  @Column(length = 255)
  private String picture;

  @Column(length = 2000)
  private String description;

  @ManyToOne
  @JoinColumn(name = "owner_id", nullable = false)
  private User owner;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
}
