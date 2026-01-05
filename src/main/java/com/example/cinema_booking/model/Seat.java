package com.example.cinema_booking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "siedzenia")
public class Seat {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private int rowNum;

    private int seatNum;

    @ManyToOne
    @JoinColumn(name = "room_id") //wiele siedzen ma jedna sale
    private Room room;
}
