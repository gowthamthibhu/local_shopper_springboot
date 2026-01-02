package com.localshopper.backend.Service;

import com.localshopper.backend.Model.*;
import com.localshopper.backend.Repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final DiscountOfferRepository offerRepository;
    private final ItemRepository itemRepository;
    private final PickupSlotRepository slotRepository;

    public BookingService(
            BookingRepository bookingRepository,
            UserRepository userRepository,
            DiscountOfferRepository offerRepository,
            ItemRepository itemRepository,
            PickupSlotRepository slotRepository
    ) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
        this.itemRepository = itemRepository;
        this.slotRepository = slotRepository;
    }

    @Transactional
    public Booking createBooking(
            Long userId,
            Long offerId,
            Long pickupSlotId,
            int quantity
    ) {
        User customer = userRepository.findById(userId).orElseThrow();

        if (customer.getRole() != UserRole.CUSTOMER) {
            throw new RuntimeException("Only customers can book items");
        }

        DiscountOffer offer = offerRepository.findById(offerId).orElseThrow();

        // validate offer time
        LocalDateTime now = LocalDateTime.now();
        if (!offer.isActive()
                || now.isBefore(offer.getStartTime())
                || now.isAfter(offer.getEndTime())) {
            throw new RuntimeException("Offer is not active");
        }

        Item item = offer.getItem();

        // 🔥 atomic stock decrement
        int updated = itemRepository
                .decrementQuantityIfAvailable(item.getId(), quantity);

        if (updated == 0) {
            throw new RuntimeException("Insufficient item quantity");
        }

        // 🔥 atomic pickup slot increment
        int slotUpdated = slotRepository
                .incrementCurrentPickupsIfSpace(pickupSlotId, 1);

        if (slotUpdated == 0) {
            throw new RuntimeException("Pickup slot is full or disabled");
        }

        PickupSlot slot = slotRepository.findById(pickupSlotId).orElseThrow();

        if (now.isAfter(slot.getEndTime())) {
            throw new RuntimeException("Pickup slot already ended");
        }

        if (now.isBefore(slot.getStartTime())) {
            throw new RuntimeException("Pickup slot not started yet");
        }

        if (!slot.isEnabled()) {
            throw new RuntimeException("Pickup slot is disabled");
        }

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setDiscountOffer(offer);
        booking.setPickupSlot(slot);
        booking.setPickupTime(slot.getStartTime());
        booking.setQuantity(quantity);

        return bookingRepository.save(booking);
    }
}
