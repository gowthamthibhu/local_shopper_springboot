package com.localshopper.backend.Repository;

import com.localshopper.backend.Model.DiscountOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DiscountOfferRepository extends JpaRepository<DiscountOffer, Long> {

    List<DiscountOffer> findByActiveTrue();

    List<DiscountOffer> findByStartTimeBeforeAndEndTimeAfter(
            LocalDateTime now1,
            LocalDateTime now2
    );
}
