package com.localshopper.backend.Controller;

import com.localshopper.backend.Model.Booking;
import com.localshopper.backend.Service.BookingService;
import com.localshopper.backend.dto.BookingRequest;
import com.localshopper.backend.dto.BookingResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public BookingResponse createBooking(@RequestBody BookingRequest request) {

        Booking booking = bookingService.createBooking(
                request.userId,
                request.offerId,
                request.pickupSlotId,
                request.quantity
        );

        return new BookingResponse(
                booking.getId(),
                booking.getDiscountOffer().getItem().getItemName(),
                booking.getQuantity(),
                booking.getDiscountOffer().getItem().getDiscountPrice(),
                booking.getPickupTime()
        );
    }
}
