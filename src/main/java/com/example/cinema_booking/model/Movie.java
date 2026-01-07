package com.example.cinema_booking.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE) //tylko dla buildera
@Entity
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
