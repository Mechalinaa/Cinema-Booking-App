package com.example.cinema_booking.repositories;

import com.example.cinema_booking.models.Room;
import com.example.cinema_booking.models.Seat;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class SeatRepositoryTest {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private RoomRepository roomRepository;

    private Seat seat1;
    private Seat seat2;
    private Room room;

    @BeforeEach
    void setUp() {
        seatRepository.deleteAll();

        room = new Room();
        room.setName("Room A");

        seat1 = new Seat();
        seat1.setRowNum(1);
        seat1.setSeatNum(1);
        seat1.setRoom(room);

        seat2 = new Seat();
        seat2.setRowNum(1);
        seat2.setSeatNum(2);
        seat2.setRoom(room);

        room.setSeats(new ArrayList<>(List.of(seat1, seat2)));

        roomRepository.save(room);
    }

    @Test
    void shouldSaveAndFindSeatById() {
        Seat found = seatRepository.findByIdOrElseThrow(seat1.getId());
        assertThat(found).isNotNull();
        assertThat(found.getRowNum()).isEqualTo(1);
        assertThat(found.getSeatNum()).isEqualTo(1);
        assertThat(found.getRoom()).isEqualTo(room);
    }

    @Test
    void findByIdOrElseThrow_shouldReturnSeat() {
        Seat found = seatRepository.findByIdOrElseThrow(seat2.getId());
        assertThat(found).isNotNull();
        assertThat(found.getRowNum()).isEqualTo(1);
        assertThat(found.getSeatNum()).isEqualTo(2);
        assertThat(found.getRoom()).isEqualTo(room);
    }

    @Test
    void findByIdOrElseThrow_shouldThrowExceptionIfNotFound() {
        UUID randomId = UUID.randomUUID();
        assertThatThrownBy(() -> seatRepository.findByIdOrElseThrow(randomId))
                .isInstanceOfAny(EntityNotFoundException.class,
                        org.springframework.orm.jpa.JpaObjectRetrievalFailureException.class);
    }

    @Test
    void shouldFindAllSeats() {
        List<Seat> seats = seatRepository.findAll();
        assertThat(seats)
                .hasSize(2)
                .extracting(Seat::getSeatNum)
                .containsExactlyInAnyOrder(1, 2);
    }

    @Test
    void shouldDeleteSeatThroughRoom() {
        room.getSeats().remove(seat1);
        roomRepository.save(room);

        List<Seat> seats = seatRepository.findAll();
        assertThat(seats)
                .hasSize(1)
                .extracting(Seat::getSeatNum)
                .containsExactly(2);
    }
}
