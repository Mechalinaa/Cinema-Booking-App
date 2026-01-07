package com.example.cinema_booking.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "sale")
public class Room {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private int rows;

    private int seatsPerRow;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL) //jedna sala ma wiele siedzen
    private List<Seat> seats;
}
