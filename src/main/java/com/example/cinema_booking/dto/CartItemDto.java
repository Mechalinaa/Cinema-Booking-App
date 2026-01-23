package com.example.cinema_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CartItemDto {
    private UUID seatId;
    private int rowNum;
    private int seatNum;
    private String ticketType;
    private BigDecimal price;
}
