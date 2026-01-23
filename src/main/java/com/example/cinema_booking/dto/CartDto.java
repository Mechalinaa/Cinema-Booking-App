package com.example.cinema_booking.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CartDto {
    private UUID showtimeId;
    private List<CartItemDto> items = new ArrayList<>();

    public BigDecimal getTotalPrice() {
        return items.stream()
                .map(CartItemDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
