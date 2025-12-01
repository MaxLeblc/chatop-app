package com.chatop.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatop.api.model.Rental;
import com.chatop.api.model.User;

/**
 * Repository interface for Rental entity
 * Provides CRUD operations and custom queries
 */
@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

    /**
     * Find all rentals by owner
     * @param owner the rental owner
     * @return list of rentals owned by the user
     */
    List<Rental> findByOwner(User owner);
}
