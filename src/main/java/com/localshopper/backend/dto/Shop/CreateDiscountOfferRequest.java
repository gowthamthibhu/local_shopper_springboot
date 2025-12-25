package com.localshopper.backend.dto.Shop;

import com.localshopper.backend.Model.DiscountType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateDiscountOfferRequest {
    private String dealName;
    private DiscountType discountType;
    private double discountValue;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
