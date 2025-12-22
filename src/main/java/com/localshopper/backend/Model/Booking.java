package com.localshopper.backend.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime pickupTime;
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_offer_id", nullable = false)
    private DiscountOffer discountOffer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pickup_slot_id", nullable = false)
    private PickupSlot pickupSlot;
}
