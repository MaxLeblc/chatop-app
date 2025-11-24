package com.chatop.api.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.chatop.api.dto.RentalDto;
import com.chatop.api.dto.RentalCreateRequest;
import com.chatop.api.dto.RentalUpdateRequest;
import com.chatop.api.exception.ImageUploadException;
import com.chatop.api.exception.ResourceNotFoundException;
import com.chatop.api.model.Rental;
import com.chatop.api.model.User;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;

/**
 * Service class for managing rentals.
 */
@Service
public class RentalService {

  private final RentalRepository rentalRepository;
  private final UserRepository userRepository;
  private final CloudinaryService cloudinaryService;

  public RentalService(RentalRepository rentalRepository, UserRepository userRepository, CloudinaryService cloudinaryService) {
    this.rentalRepository = rentalRepository;
    this.userRepository = userRepository;
    this.cloudinaryService = cloudinaryService;
  }

  /**
   * Create a new rental
   */
  public RentalDto createRental(RentalCreateRequest request, String ownerEmail) {
    User owner = userRepository.findByEmail(ownerEmail)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + ownerEmail));

    // Upload image to Cloudinary
    String pictureUrl;
    try {
      pictureUrl = cloudinaryService.uploadImage(request.getPicture());
    } catch (IOException ex) {
      throw new ImageUploadException("Failed to upload image to Cloudinary", ex);
    }

    Rental rental = new Rental();
    rental.setName(request.getName());
    rental.setSurface(request.getSurface());
    rental.setPrice(request.getPrice());
    rental.setPicture(pictureUrl);
    rental.setDescription(request.getDescription());
    rental.setOwner(owner);

    Rental savedRental = rentalRepository.save(rental);

    return convertToDto(savedRental);
  }

  /**
   * Get all rentals
   */
  public List<RentalDto> getAllRentals() {
    List<Rental> rentals = rentalRepository.findAll();

    return rentals.stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());
  }

  /**
   * Get rental by ID
   */
  public RentalDto getRentalById(Integer id) {
    Rental rental = rentalRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id: " + id));
    return convertToDto(rental);
  }

  /**
   * Update rental by ID
   */
  public RentalDto updateRental(Integer id, RentalUpdateRequest request) {
    Rental rental = rentalRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id: " + id));

    if (request.getName() != null) {
      rental.setName(request.getName());
    }
    if (request.getSurface() != null) {
      rental.setSurface(request.getSurface());
    }
    if (request.getPrice() != null) {
      rental.setPrice(request.getPrice());
    }
    if (request.getPicture() != null && !request.getPicture().isEmpty()) {
      try {
        String pictureUrl = cloudinaryService.uploadImage(request.getPicture());
        rental.setPicture(pictureUrl);
      } catch (IOException ex) {
        throw new ImageUploadException("Failed to upload image to Cloudinary", ex);
      }
    }
    if (request.getDescription() != null) {
      rental.setDescription(request.getDescription());
    }

    Rental updatedRental = rentalRepository.save(rental);

    return convertToDto(updatedRental);
  }

  /**
   * Delete rental by ID
   */
  public void deleteRental(Integer id) {
    Rental rental = rentalRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id: " + id));
    rentalRepository.delete(rental);
  }

  /**
   * Map Rental entity to RentalDto
   */
  private RentalDto convertToDto(Rental rental) {
    RentalDto dto = new RentalDto();
    dto.setId(rental.getId());
    dto.setName(rental.getName());
    dto.setSurface(rental.getSurface());
    dto.setPrice(rental.getPrice());
    dto.setPicture(rental.getPicture());
    dto.setDescription(rental.getDescription());
    dto.setOwnerId(rental.getOwner().getId());
    dto.setCreatedAt(rental.getCreatedAt());
    dto.setUpdatedAt(rental.getUpdatedAt());
    return dto;
  }
}
