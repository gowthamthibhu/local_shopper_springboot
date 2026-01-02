package com.localshopper.backend.Service;

import com.localshopper.backend.Repository.PickupSlotRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PickupSlotCleanupService {

    private final PickupSlotRepository repo;

    public PickupSlotCleanupService(PickupSlotRepository repo) {
        this.repo = repo;
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void disableExpiredSlots() {
        repo.disableExpiredSlots();
    }
}

