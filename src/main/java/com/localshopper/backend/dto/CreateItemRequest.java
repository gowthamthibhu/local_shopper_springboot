package com.localshopper.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateItemRequest {
    private String itemName;
    private String description;
    private double price;
    private int quantity;
}
