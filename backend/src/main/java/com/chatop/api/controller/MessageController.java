package com.chatop.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.dto.MessageRequest;
import com.chatop.api.dto.MessageResponse;
import com.chatop.api.service.MessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * REST controller for managing messages
 */
@RestController
@RequestMapping("/api/messages")
@Validated
@Tag(name = "Messages", description = "Message management")
@SecurityRequirement(name = "Bearer Authentication")
public class MessageController {

  private final MessageService messageService;

  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  /**
   * POST /api/messages
   * Create a new message
   */
  @Operation(summary = "Create a new message", description = "Post a new message to a rental (authentication required)")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Message sent successfully", content = @Content(schema = @Schema(implementation = MessageResponse.class))),
      @ApiResponse(responseCode = "400", description = "Invalid data"),
      @ApiResponse(responseCode = "403", description = "Invalid or missing JWT token")
  })
  @PostMapping
  public ResponseEntity<MessageResponse> createMessage(
      @Valid @RequestBody MessageRequest request,
      Authentication authentication) {
    String userEmail = authentication.getName();
    MessageResponse response = messageService.createMessage(request, userEmail);
    return ResponseEntity.ok(response);
  }
}
