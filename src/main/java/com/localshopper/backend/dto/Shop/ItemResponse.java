package com.localshopper.backend.dto.Shop;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemResponse {
    private Long id;
    private String itemName;
    private String description;
    private String shopName;
    private double price;
    private double discountPrice;
    private int quantity;
}
