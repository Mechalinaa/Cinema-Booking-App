package com.example.cinema_booking.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Movie {
    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private int ageRestriction;

    @Lob
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String director;

    @ElementCollection
    private List<String> actors;

    @ElementCollection
    private List<String> trailers;

    @ElementCollection
    private List<String> gallery;

    @Column(nullable = false)
    private int durationInMinutes;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Showtime> showtimes;
}
