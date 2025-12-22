package com.localshopper.backend.Controller;

import com.localshopper.backend.Model.Shop;
import com.localshopper.backend.Service.ShopService;
import com.localshopper.backend.dto.CreateShopRequest;
import com.localshopper.backend.dto.ShopResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
public class ShopController {

    private final ShopService shopService;

    // ✅ inject service (NOT repository)
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    // ✅ ONLY ONE POST METHOD
    @PostMapping
    public ShopResponse createShop(
            @RequestParam Long ownerId,
            @RequestBody CreateShopRequest request
    ) {
        Shop shop = shopService.createShop(ownerId, request);

        return new ShopResponse(
                shop.getId(),
                shop.getShopName(),
                shop.getAddress(),
                shop.getCity(),
                shop.getArea()
        );
    }

    @GetMapping
    public List<ShopResponse> getAllShops(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String area
    ) {
        return shopService.getNearbyShops(city, area)
                .stream()
                .map(shop -> new ShopResponse(
                        shop.getId(),
                        shop.getShopName(),
                        shop.getAddress(),
                        shop.getCity(),
                        shop.getArea()
                ))
                .toList();
    }
}
