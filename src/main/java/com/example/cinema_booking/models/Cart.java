package com.example.cinema_booking.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Cart {
    private UUID showtimeId;
    private List<Ticket> tickets = new ArrayList<>();

    public BigDecimal getTotalPrice() {
        return tickets.stream()
                .map(Ticket::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
