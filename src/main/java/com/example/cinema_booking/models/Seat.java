package com.example.cinema_booking.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Seat {
    @Id
    @UuidGenerator
    @GeneratedValue
    private UUID id;
    private int rowNum;
    private int seatNum;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_id")
    private Room room;
}
