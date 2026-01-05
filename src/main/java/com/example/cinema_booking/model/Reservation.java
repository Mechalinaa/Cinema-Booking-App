package com.example.cinema_booking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "rezerwacje")
public class Reservation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id

    private Long id;

    @Column(unique = true)
    private String ticketId;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    @ManyToMany
    @JoinTable(
            name = "rezerwacja_siedzenia",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    private List<Seat> seats;
}
