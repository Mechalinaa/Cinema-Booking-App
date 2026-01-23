package com.example.cinema_booking.services;

import com.example.cinema_booking.models.*;
import com.example.cinema_booking.repositories.ShowtimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ShowtimeRepository showtimeRepository;

    public Cart addSeatsToCart(Cart cart, UUID showtimeId, Iterable<UUID> seatIds) {
        var showtime = showtimeRepository.findByIdOrElseThrow(showtimeId);

        for (var seat : showtime.getRoom().getSeats()) {
            if (seatIds.iterator().hasNext() && seatIds.iterator().next().equals(seat.getId())) {
                Ticket ticket = new Ticket();
                ticket.setSeatId(seat.getId());
                ticket.setSeatLabel("Rząd " + seat.getRowNum() + ", miejsce " + seat.getSeatNum());
                ticket.setType(TicketType.NORMALNY);
                ticket.setPrice(BigDecimal.valueOf(25)); //default

                cart.getTickets().add(ticket);
            }
        }

        cart.setShowtimeId(showtimeId);
        return cart;
    }

    public void updateTicketType(Ticket ticket, TicketType type) {
        ticket.setType(type);

        switch (type) {
            case NORMALNY -> ticket.setPrice(BigDecimal.valueOf(25));
            case ULGOWY -> ticket.setPrice(BigDecimal.valueOf(18));
            case RODZINNY -> ticket.setPrice(BigDecimal.valueOf(15));
        }
    }
}
