package com.localshopper.backend.dto.Shop;

import com.localshopper.backend.Model.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class ShopResponse {
    private Long id;
    private String shopName;
    private String address;
    private String city;
    private String area;
    private boolean open;
    private LocalTime openingTime;
    private LocalTime closingTime;

    // ⭐ convenience factory method
    public static ShopResponse fromEntity(Shop shop) {
        return new ShopResponse(
                shop.getId(),
                shop.getShopName(),
                shop.getAddress(),
                shop.getCity(),
                shop.getArea(),
                shop.isOpen(),
                shop.getOpeningTime(),
                shop.getClosingTime()
        );
    }
}
