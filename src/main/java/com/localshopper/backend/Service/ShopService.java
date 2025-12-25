package com.localshopper.backend.Service;

import com.localshopper.backend.Model.Shop;
import com.localshopper.backend.Model.User;
import com.localshopper.backend.Model.UserRole;
import com.localshopper.backend.Repository.ShopRepository;
import com.localshopper.backend.Repository.UserRepository;
import com.localshopper.backend.dto.Shop.CreateShopRequest;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class ShopService {

    private final ShopRepository shopRepository;
    private final UserRepository userRepository;

    public ShopService(ShopRepository shopRepository, UserRepository userRepository) {
        this.shopRepository = shopRepository;
        this.userRepository = userRepository;
    }

    public Shop createShop(Long ownerId, CreateShopRequest request) {

        User owner = userRepository.findById(ownerId).orElseThrow();

        if (owner.getRole() != UserRole.SHOP_OWNER) {
            throw new RuntimeException("Only shop owners can create shops");
        }

        Shop shop = new Shop();
        shop.setShopName(request.getShopName());
        shop.setAddress(request.getAddress());
        shop.setCity(request.getCity());
        shop.setArea(request.getArea());
        shop.setOwner(owner);

        shop.setOpen(request.getOpen() != null ? request.getOpen() : false);
        shop.setOpeningTime(
                request.getOpeningTime() != null ? request.getOpeningTime() : LocalTime.of(9,0)
        );
        shop.setClosingTime(
                request.getClosingTime() != null ? request.getClosingTime() : LocalTime.of(21,0)
        );


        return shopRepository.save(shop);
    }

    public void setShopOpenStatus(Long shopId, boolean open) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("Shop not found"));

        shop.setOpen(open);
        shopRepository.save(shop);
    }



    public List<Shop> getNearbyShops(String city, String area) {
        if (city != null && area != null) {
            return shopRepository.findByCityAndArea(city, area);
        }
        return shopRepository.findAll();
    }

    public List<Shop> getOwnerShops(Long ownerId) {

        // ensure user exists and is shop owner
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        if (owner.getRole() != UserRole.SHOP_OWNER) {
            throw new RuntimeException("Only shop owners can view owned shops");
        }

        // return ONLY their shops
        return shopRepository.findByOwnerId(ownerId);
    }

    public List<Shop> getOpenShopsByCity(String city) {
        return shopRepository.findByCityAndOpenTrue(city);
    }

    public boolean isCurrentlyOpen(Shop shop) {
        LocalTime now = LocalTime.now();
        return now.isAfter(shop.getOpeningTime()) && now.isBefore(shop.getClosingTime());
    }



}
