package com.localshopper.backend.Repository;

import com.localshopper.backend.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByShopId(Long shopId);

    // 🔥 IMPORTANT: atomic quantity reduction
    @Transactional
    @Modifying
    @Query("""
        UPDATE Item i
        SET i.quantity = i.quantity - :qty
        WHERE i.id = :itemId AND i.quantity >= :qty
    """)
    int decrementQuantityIfAvailable(Long itemId, int qty);
}
