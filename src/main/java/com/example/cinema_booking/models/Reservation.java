package com.example.cinema_booking.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reservation {

    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID id;

    @ElementCollection
    private List<String> seats;

    @Column(nullable = false)
    private String movieName;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private BigDecimal totalPrice;
}
