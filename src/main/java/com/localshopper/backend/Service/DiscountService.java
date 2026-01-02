package com.localshopper.backend.Service;

import com.localshopper.backend.Model.DiscountOffer;
import com.localshopper.backend.Repository.DiscountOfferRepository;
import com.localshopper.backend.dto.Shop.DiscountOfferResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DiscountService {

    private final DiscountOfferRepository offerRepository;

    public DiscountService(DiscountOfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public List<DiscountOfferResponse> getOffersByShop(Long shopId) {
        return offerRepository
                .findByItem_Shop_IdAndActiveTrue(shopId)
                .stream()
                .map(DiscountOfferResponse::fromEntity)
                .toList();
    }
}
