package com.example.cinema_booking.bin;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class CartItem {
    private UUID showId;
    private int row;
    private int seat;
}
