package com.localshopper.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequest {
    public Long userId;
    public Long offerId;
    public Long pickupSlotId;
    public int quantity;
}
