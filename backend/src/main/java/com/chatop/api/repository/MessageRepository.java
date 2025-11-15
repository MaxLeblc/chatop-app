package com.chatop.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatop.api.model.Message;
import com.chatop.api.model.Rental;
import com.chatop.api.model.User;

/**
 * Repository interface for Message entity
 * Provides CRUD operations and custom queries
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    /**
     * Find all messages for a specific rental
     * @param rental the rental
     * @return list of messages for the rental
     */
    List<Message> findByRental(Rental rental);

    /**
     * Find all messages sent by a specific user
     * @param user the user
     * @return list of messages sent by the user
     */
    List<Message> findByUser(User user);
}
