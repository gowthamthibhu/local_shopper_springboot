package com.localshopper.backend.Controller;

import com.localshopper.backend.Model.Item;
import com.localshopper.backend.Service.ItemService;
import com.localshopper.backend.dto.CreateItemRequest;
import com.localshopper.backend.dto.ItemResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // ✅ CREATE ITEM (DTO-based, SAFE)
    @PostMapping
    public ItemResponse createItem(
            @RequestParam Long shopId,
            @RequestBody CreateItemRequest request
    ) {
        Item item = itemService.addItem(shopId, request);

        return new ItemResponse(
                item.getId(),
                item.getItemName(),
                item.getDescription(),
                item.getPrice(),
                item.getDiscountPrice(),
                item.getQuantity()
        );
    }

    // ✅ GET ITEMS BY SHOP
    @GetMapping("/shop/{shopId}")
    public List<ItemResponse> getItemsByShop(@PathVariable Long shopId) {
        return itemService.getItemsByShop(shopId)
                .stream()
                .map(item -> new ItemResponse(
                        item.getId(),
                        item.getItemName(),
                        item.getDescription(),
                        item.getPrice(),
                        item.getDiscountPrice(),
                        item.getQuantity()
                ))
                .toList();
    }
}
