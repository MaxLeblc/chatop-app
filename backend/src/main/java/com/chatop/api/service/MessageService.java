package com.chatop.api.service;

import org.springframework.stereotype.Service;

import com.chatop.api.dto.MessageRequest;
import com.chatop.api.dto.MessageResponse;
import com.chatop.api.model.Message;
import com.chatop.api.model.Rental;
import com.chatop.api.model.User;
import com.chatop.api.repository.MessageRepository;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;

@Service
public class MessageService {

  private final MessageRepository messageRepository;
  private final UserRepository userRepository;
  private final RentalRepository rentalRepository;

  public MessageService(MessageRepository messageRepository, UserRepository userRepository,
      RentalRepository rentalRepository) {
    this.messageRepository = messageRepository;
    this.userRepository = userRepository;
    this.rentalRepository = rentalRepository;
  }

  /**
   * Create a new message
   */
  public MessageResponse createMessage(MessageRequest request, String userEmail) {
    try {
      User user = userRepository.findByEmail(userEmail)
          .orElseThrow(() -> new RuntimeException("User not found"));

      Rental rental = rentalRepository.findById(request.getRentalId())
          .orElseThrow(() -> new RuntimeException("Rental not found"));

      Message message = new Message();
      message.setMessage(request.getMessage());
      message.setUser(user);
      message.setRental(rental);

      messageRepository.save(message);

      return new MessageResponse("Message sent successfully");
    } catch (Exception ex) {
      throw new RuntimeException("Failed to create message: " + ex.getMessage(), ex);
    }
  }
}
