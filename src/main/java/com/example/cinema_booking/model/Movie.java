package com.example.cinema_booking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Filmy")
public class Movie {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false) //nie moze byc pusty tytul
    private String title;

    private String genre;

    private int durationInMinutes;

}
