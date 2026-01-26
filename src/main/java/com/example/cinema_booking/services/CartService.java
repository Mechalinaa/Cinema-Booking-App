package com.example.cinema_booking.services;

import com.example.cinema_booking.dto.CartDto;
import com.example.cinema_booking.dto.CartItemDto;
import com.example.cinema_booking.models.Seat;
import com.example.cinema_booking.models.TicketType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {

    private final SeatService seatService;

    public CartDto getOrCreateCart(CartDto cart, UUID showtimeId) {
        if (cart == null) {
            cart = new CartDto();
            cart.setShowtimeId(showtimeId);
        }
        return cart;
    }

    public void addSeats(CartDto cart, List<UUID> seatIds) {

        for (UUID seatId : seatIds) {

            boolean exists = cart.getItems().stream()
                    .anyMatch(i -> i.getSeatId().equals(seatId));

            if (exists) continue;

            Seat seat = seatService.findById(seatId);

            cart.getItems().add(new CartItemDto(
                    seat.getId(),
                    seat.getRowNum(),
                    seat.getSeatNum(),
                    TicketType.NORMALNY.name(),
                    priceFor(TicketType.NORMALNY)
            ));
        }
    }

    public void updateTicketType(CartDto cart, UUID seatId, TicketType type) {
        cart.getItems().stream()
                .filter(i -> i.getSeatId().equals(seatId))
                .findFirst()
                .ifPresent(item -> {
                    item.setTicketType(type.name());
                    item.setPrice(priceFor(type));
                });
    }

    public void removeSeat(CartDto cart, UUID seatId) {
        cart.getItems().removeIf(i -> i.getSeatId().equals(seatId));
    }

    private BigDecimal priceFor(TicketType type) {
        return switch (type) {
            case NORMALNY -> BigDecimal.valueOf(25);
            case ULGOWY -> BigDecimal.valueOf(18);
            case RODZINNY -> BigDecimal.valueOf(15);
        };
    }
}
