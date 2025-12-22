package com.localshopper.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DiscountOfferResponse {

    private Long id;
    private String dealName;
    private double discountValue;
    private String discountType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Long itemId;
    private String itemName;
}
