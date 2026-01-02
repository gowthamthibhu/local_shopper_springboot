package com.localshopper.backend.Service;

import com.localshopper.backend.Repository.DiscountOfferRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DiscountCleanupService {

    private final DiscountOfferRepository discountOfferRepository;

    public DiscountCleanupService(DiscountOfferRepository repo) {
        this.discountOfferRepository = repo;
    }

    @Scheduled(fixedRate = 60000) // every 1 minute
    @Transactional
    public void expireDiscounts() {
        int updated = discountOfferRepository.deactivateExpiredOffers();
        System.out.println("Expired offers deactivated = " + updated);
    }
}

