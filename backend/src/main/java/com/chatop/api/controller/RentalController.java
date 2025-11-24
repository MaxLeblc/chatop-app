package com.chatop.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.chatop.api.dto.RentalDto;
import com.chatop.api.dto.RentalCreateRequest;
import com.chatop.api.dto.RentalUpdateRequest;
import com.chatop.api.dto.RentalsResponse;
import com.chatop.api.service.RentalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * REST Controller for rental operations
 */
@RestController
@RequestMapping("/api/rentals")
@Validated
@Tag(name = "Rentals", description = "Rental management")
@SecurityRequirement(name = "Bearer Authentication")
public class RentalController {

  private final RentalService rentalService;

  public RentalController(RentalService rentalService) {
    this.rentalService = rentalService;
  }

  /**
   * POST /api/rentals
   * Create a new rental
   */
  @Operation(summary = "Create a new rental", description = "Create a rental listing (authentication required)")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Rental created successfully", content = @Content(schema = @Schema(implementation = RentalDto.class))),
      @ApiResponse(responseCode = "400", description = "Invalid data"),
      @ApiResponse(responseCode = "403", description = "Invalid or missing JWT token")
  })
  @PostMapping(consumes = "multipart/form-data")
  public ResponseEntity<RentalDto> createRental(
      @ModelAttribute @Valid RentalCreateRequest request,
      Authentication authentication) {
    String ownerEmail = authentication.getName();
    RentalDto rental = rentalService.createRental(request, ownerEmail);
    return ResponseEntity.status(HttpStatus.CREATED).body(rental);
  }

  /**
   * GET /api/rentals
   * Get all rentals
   */
  @Operation(summary = "Get all rentals", description = "Retrieve all rental listings")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Rentals retrieved successfully"),
      @ApiResponse(responseCode = "403", description = "Invalid or missing JWT token")
  })
  @GetMapping
  public ResponseEntity<RentalsResponse> getAllRentals() {
    List<RentalDto> rentals = rentalService.getAllRentals();
    RentalsResponse response = new RentalsResponse(rentals);
    return ResponseEntity.ok(response);
  }

  /**
   * GET /api/rentals/{id}
   * Get rental by ID
   */
  @Operation(summary = "Get rental by ID", description = "Retrieve a specific rental by its ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Rental found", content = @Content(schema = @Schema(implementation = RentalDto.class))),
      @ApiResponse(responseCode = "404", description = "Rental not found"),
      @ApiResponse(responseCode = "403", description = "Invalid or missing JWT token")
  })
  @GetMapping("/{id}")
  public ResponseEntity<RentalDto> getRentalById(@PathVariable Integer id) {
    RentalDto rental = rentalService.getRentalById(id);
    return ResponseEntity.ok(rental);
  }

  /**
   * PUT /api/rentals/{id}
   * Update rental by ID
   */
  @Operation(summary = "Update rental", description = "Update an existing rental by its ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Rental updated successfully", content = @Content(schema = @Schema(implementation = RentalDto.class))),
      @ApiResponse(responseCode = "400", description = "Invalid data"),
      @ApiResponse(responseCode = "404", description = "Rental not found"),
      @ApiResponse(responseCode = "403", description = "Invalid or missing JWT token")
  })
  @PutMapping(value = "/{id}", consumes = "multipart/form-data")
  public ResponseEntity<RentalDto> updateRental(@PathVariable Integer id,
      @ModelAttribute @Valid RentalUpdateRequest request) {
    RentalDto rental = rentalService.updateRental(id, request);
    return ResponseEntity.ok(rental);
  }
}
