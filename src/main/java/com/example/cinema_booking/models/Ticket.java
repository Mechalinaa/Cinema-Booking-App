package com.example.cinema_booking.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class Ticket {
    private UUID seatId;
    private String seatLabel;
    private TicketType type;
    private BigDecimal price;
}
