package com.localshopper.backend.Controller;

import com.localshopper.backend.Model.PickupSlot;
import com.localshopper.backend.Model.Shop;
import com.localshopper.backend.Repository.PickupSlotRepository;
import com.localshopper.backend.Repository.ShopRepository;
import com.localshopper.backend.dto.PickupSlotResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pickup-slots")
public class PickupSlotController {

    private final PickupSlotRepository slotRepository;
    private final ShopRepository shopRepository;

    public PickupSlotController(PickupSlotRepository slotRepository,
                                ShopRepository shopRepository) {
        this.slotRepository = slotRepository;
        this.shopRepository = shopRepository;
    }

    @PostMapping
    public PickupSlotResponse createSlot(
            @RequestParam Long shopId,
            @RequestBody PickupSlot slot
    ) {
        Shop shop = shopRepository.findById(shopId).orElseThrow();
        slot.setShop(shop);

        PickupSlot saved = slotRepository.save(slot);

        return new PickupSlotResponse(
                saved.getId(),
                saved.getStartTime(),
                saved.getEndTime(),
                saved.getMaxPickups(),
                saved.getCurrentPickups(),
                saved.isEnabled()
        );
    }


    @GetMapping("/shop/{shopId}")
    public List<PickupSlotResponse> getSlots(@PathVariable Long shopId) {
        return slotRepository.findByShopIdAndEnabledTrue(shopId)
                .stream()
                .map(slot -> new PickupSlotResponse(
                        slot.getId(),
                        slot.getStartTime(),
                        slot.getEndTime(),
                        slot.getMaxPickups(),
                        slot.getCurrentPickups(),
                        slot.isEnabled()
                ))
                .toList();
    }
}

