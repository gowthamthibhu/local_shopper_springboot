package com.localshopper.backend.dto.Shop;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShopResponse {
    private Long id;
    private String shopName;
    private String address;
    private String city;
    private String area;
}
