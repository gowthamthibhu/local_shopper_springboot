package com.localshopper.backend.Controller;

import com.localshopper.backend.Model.DiscountOffer;
import com.localshopper.backend.Model.Item;
import com.localshopper.backend.Repository.DiscountOfferRepository;
import com.localshopper.backend.Repository.ItemRepository;
import com.localshopper.backend.dto.Shop.DiscountOfferResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
public class DiscountOfferController {

    private final DiscountOfferRepository offerRepository;
    private final ItemRepository itemRepository;

    public DiscountOfferController(DiscountOfferRepository offerRepository,
                                   ItemRepository itemRepository) {
        this.offerRepository = offerRepository;
        this.itemRepository = itemRepository;
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

        return new DiscountOfferResponse(
                saved.getId(),
                saved.getDealName(),
                saved.getDiscountValue(),
                saved.getDiscountType().name(),
                saved.getStartTime(),
                saved.getEndTime(),
                item.getId(),
                item.getItemName()
        );
    }

    @GetMapping("/active")
    public List<DiscountOfferResponse> getActiveOffers() {
        return offerRepository.findByActiveTrue()
                .stream()
                .map(o -> new DiscountOfferResponse(
                        o.getId(),
                        o.getDealName(),
                        o.getDiscountValue(),
                        o.getDiscountType().name(),
                        o.getStartTime(),
                        o.getEndTime(),
                        o.getItem().getShop().getId(),   // ✅ shopId
                        o.getItem().getItemName()        // optional but useful
                ))
                .toList();
    }
}
