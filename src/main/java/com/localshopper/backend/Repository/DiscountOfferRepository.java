package com.localshopper.backend.Repository;

import com.localshopper.backend.Model.DiscountOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface DiscountOfferRepository extends JpaRepository<DiscountOffer, Long> {

    List<DiscountOffer> findByActiveTrue();

    List<DiscountOffer> findByStartTimeBeforeAndEndTimeAfter(
            LocalDateTime now1,
            LocalDateTime now2
    );

    @Modifying
    @Query("""
    UPDATE DiscountOffer d
    SET d.active = false
    WHERE d.endTime < CURRENT_TIMESTAMP
      AND d.active = true
""")
    int deactivateExpiredOffers();

    List<DiscountOffer> findByItem_Shop_IdAndActiveTrue(Long shopId);


}
