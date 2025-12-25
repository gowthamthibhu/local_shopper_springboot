package com.localshopper.backend.dto.Shop;

import com.localshopper.backend.Model.DiscountOffer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DiscountOfferResponse {
    private Long id;
    private String dealName;
    private double discountValue;
    private String discountType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Long shopId;
    private String shopName;
    private boolean shopOpen;
    private String itemName;

    public static DiscountOfferResponse fromEntity(DiscountOffer offer){
        return new DiscountOfferResponse(
                offer.getId(),
                offer.getDealName(),
                offer.getDiscountValue(),
                offer.getDiscountType().name(),
                offer.getStartTime(),
                offer.getEndTime(),

                // SHOP COMES THROUGH ITEM
                offer.getItem().getShop().getId(),
                offer.getItem().getShop().getShopName(),
                offer.getItem().getShop().isOpen(),   // 👈 THIS IS THE VALUE YOU NEED

                offer.getItem().getItemName()
        );
    }

}





