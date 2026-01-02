package com.localshopper.backend.Repository;

import com.localshopper.backend.Model.PickupSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PickupSlotRepository extends JpaRepository<PickupSlot, Long> {

    List<PickupSlot> findByShopIdAndEnabledTrue(Long shopId);

    //IMPORTANT: atomic slot capacity update
    @Transactional
    @Modifying
    @Query("""
        UPDATE PickupSlot p
        SET p.currentPickups = p.currentPickups + :inc
        WHERE p.id = :slotId
          AND p.enabled = true
          AND p.currentPickups + :inc <= p.maxPickups
    """)
    int incrementCurrentPickupsIfSpace(Long slotId, int inc);

    @Query("""
    SELECT p FROM PickupSlot p
    WHERE p.shop.id = :shopId
      AND p.enabled = true
      AND p.endTime > CURRENT_TIMESTAMP
    """)
    List<PickupSlot> findActiveFutureSlots(Long shopId);

    @Modifying
    @Transactional
    @Query("""
    UPDATE PickupSlot p
    SET p.enabled = false
    WHERE p.endTime < CURRENT_TIMESTAMP
      AND p.enabled = true
       """)
    int disableExpiredSlots();


}
