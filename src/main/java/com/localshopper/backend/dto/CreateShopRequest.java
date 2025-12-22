package com.localshopper.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateShopRequest {
    private String shopName;
    private String address;
    private String city;
    private String area;
}
