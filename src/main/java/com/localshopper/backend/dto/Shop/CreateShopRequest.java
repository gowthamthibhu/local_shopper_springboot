package com.localshopper.backend.dto.Shop;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class CreateShopRequest {
    private String shopName;
    private String address;
    private String city;
    private String area;

    private Boolean open;
    private LocalTime openingTime;
    private LocalTime closingTime;
}
