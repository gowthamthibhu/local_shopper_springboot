package com.localshopper.backend.dto.Shop;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PickupSlotResponse {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int maxPickups;
    private int currentPickups;
    private boolean enabled;
}
