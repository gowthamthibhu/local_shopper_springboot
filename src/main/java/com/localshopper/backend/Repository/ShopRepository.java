package com.localshopper.backend.Repository;

import com.localshopper.backend.Model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    List<Shop> findByCityAndArea(String city, String area);

    List<Shop> findByOwnerId(Long ownerId);
}
