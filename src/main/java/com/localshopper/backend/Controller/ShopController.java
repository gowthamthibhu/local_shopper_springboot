package com.localshopper.backend.Controller;

import com.localshopper.backend.Model.Shop;
import com.localshopper.backend.Service.ShopService;
import com.localshopper.backend.dto.Shop.CreateShopRequest;
import com.localshopper.backend.dto.Shop.ShopResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
public class ShopController {

    private final ShopService shopService;

    // inject service
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/my")
    public ResponseEntity<List<ShopResponse>> getMyShops(@RequestParam Long ownerId) {

        List<ShopResponse> shops = shopService.getOwnerShops(ownerId)
                .stream()
                .map(ShopResponse::fromEntity)
                .toList();

        return ResponseEntity.ok(shops);
    }


    @PostMapping
    public ShopResponse createShop(
            @RequestParam Long ownerId,
            @RequestBody CreateShopRequest request
    ) {
        return ShopResponse.fromEntity(
                shopService.createShop(ownerId, request)
        );
    }

    @PatchMapping("/{shopId}/open")
    public ResponseEntity<?> toggleOpen(
            @PathVariable Long shopId,
            @RequestParam boolean open
    ) {
        shopService.setShopOpenStatus(shopId, open);
        return ResponseEntity.ok("status updated");
    }



    @GetMapping
    public List<ShopResponse> getAllShops(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String area
    ) {
        return shopService.getNearbyShops(city, area)
                .stream()
                .map(ShopResponse::fromEntity)
                .toList();
    }

    @GetMapping("/open")
    public List<ShopResponse> getOpenShops(@RequestParam String city) {

        return shopService.getOpenShopsByCity(city)
                .stream()
                .map(ShopResponse::fromEntity)
                .toList();
    }

}
