package com.localshopper.backend.dto.Shop;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreatePickupSlotRequest {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int maxPickups;
}
