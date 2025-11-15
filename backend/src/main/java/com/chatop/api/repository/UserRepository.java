package com.chatop.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatop.api.model.User;

/**
 * Repository interface for User entity
 * Provides CRUD operations and custom queries
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Find a user by email
   * 
   * @param email the user's email
   * @return Optional containing the user if found
   */
  Optional<User> findByEmail(String email);

  /**
   * Check if a user exists by email
   * 
   * @param email the user's email
   * @return true if user exists, false otherwise
   */
  boolean existsByEmail(String email);
}
