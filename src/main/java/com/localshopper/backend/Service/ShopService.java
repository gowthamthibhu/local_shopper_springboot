package com.localshopper.backend.Service;

import com.localshopper.backend.Model.Shop;
import com.localshopper.backend.Model.User;
import com.localshopper.backend.Model.UserRole;
import com.localshopper.backend.Repository.ShopRepository;
import com.localshopper.backend.Repository.UserRepository;
import com.localshopper.backend.dto.CreateShopRequest;
import org.springframework.stereotype.Service;

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

        return shopRepository.save(shop);
    }


    public List<Shop> getNearbyShops(String city, String area) {
        if (city != null && area != null) {
            return shopRepository.findByCityAndArea(city, area);
        }
        return shopRepository.findAll();
    }

    public List<Shop> getOwnerShops(Long ownerId) {
        return shopRepository.findByOwnerId(ownerId);
    }
}
