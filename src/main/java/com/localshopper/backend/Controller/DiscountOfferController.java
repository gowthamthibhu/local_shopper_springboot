package com.localshopper.backend.Controller;

import com.localshopper.backend.Model.DiscountOffer;
import com.localshopper.backend.Model.Item;
import com.localshopper.backend.Repository.DiscountOfferRepository;
import com.localshopper.backend.Repository.ItemRepository;
import com.localshopper.backend.Service.DiscountService;
import com.localshopper.backend.dto.Shop.DiscountOfferResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
public class DiscountOfferController {

    private final DiscountOfferRepository offerRepository;
    private final ItemRepository itemRepository;
    private final DiscountService discountService;

    public DiscountOfferController(DiscountOfferRepository offerRepository,
                                   ItemRepository itemRepository, DiscountService discountService) {
        this.offerRepository = offerRepository;
        this.itemRepository = itemRepository;
        this.discountService = discountService;
    }

    @GetMapping("/shop/{shopId}")
    public List<DiscountOfferResponse> getOffersForShop(@PathVariable Long shopId) {
        return discountService.getOffersByShop(shopId);
    }


    @PostMapping
    public DiscountOfferResponse createOffer(
            @RequestParam Long itemId,
            @RequestBody DiscountOffer offer
    ) {
        Item item = itemRepository.findById(itemId).orElseThrow();

        offer.setItem(item);
        offer.setActive(true);

        DiscountOffer saved = offerRepository.save(offer);

        return DiscountOfferResponse.fromEntity(saved);

    }

    @GetMapping("/active")
    public List<DiscountOfferResponse> getActiveOffers() {
        return offerRepository.findByActiveTrue()
                .stream()
                .map(DiscountOfferResponse::fromEntity)
                .toList();
    }

}
