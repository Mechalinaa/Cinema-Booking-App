package com.example.cinema_booking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatSelectionDto {
    private Long showId;
    private int row;
    private int seat;
}
