package com.example.cinema_booking.cart;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class CartItem {
    private Long showId;
    private int row;
    private int seat;
}
