package com.chatop.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response wrapper for list of rentals
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalsResponse {
    private List<RentalDto> rentals;
}
