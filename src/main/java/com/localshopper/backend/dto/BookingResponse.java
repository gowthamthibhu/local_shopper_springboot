package com.localshopper.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BookingResponse {
    private Long bookingId;
    private String itemName;
    private int quantity;
    private double finalPrice;
    private LocalDateTime pickupTime;
}

