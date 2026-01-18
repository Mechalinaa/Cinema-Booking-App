package com.example.cinema_booking.bin;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SeatSelectionDto {
    private UUID showtimeId;
    private int row;
    private int seat;
}
