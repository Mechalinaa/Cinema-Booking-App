package com.example.cinema_booking.repositories;

import com.example.cinema_booking.models.Room;
import com.example.cinema_booking.models.Seat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    private Room room1;
    private Room room2;

    @BeforeEach
    void setUp() {
        showtimeRepository.deleteAll();
        roomRepository.deleteAll();

        room1 = new Room();
        room1.setName("Sala 1");
        room1.setSeats(List.of(new Seat(), new Seat()));

        room2 = new Room();
        room2.setName("Sala 2");
        room2.setSeats(List.of(new Seat()));

        roomRepository.saveAll(List.of(room1, room2));
    }

    @Test
    void shouldSaveAndFindRoomById() {
        Room found = roomRepository.findById(room1.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Sala 1");
    }

    @Test
    void findByIdOrElseThrow_shouldReturnRoom() {
        Room found = roomRepository.findByIdOrElseThrow(room2.getId());
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Sala 2");
    }

    @Test
    void findByIdOrElseThrow_shouldThrowExceptionIfNotFound() {
        UUID randomId = UUID.randomUUID();
        assertThatThrownBy(() -> roomRepository.findByIdOrElseThrow(randomId))
                .isInstanceOfAny(EntityNotFoundException.class,
                        org.springframework.orm.jpa.JpaObjectRetrievalFailureException.class);
    }

    @Test
    void shouldFindAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        assertThat(rooms)
                .hasSize(2)
                .extracting(Room::getName)
                .containsExactlyInAnyOrder("Sala 1", "Sala 2");
    }

    @Test
    void shouldDeleteRoom_withoutBreakingFK() {
        showtimeRepository.deleteAll();

        roomRepository.delete(room1);

        List<Room> rooms = roomRepository.findAll();
        assertThat(rooms)
                .hasSize(1)
                .extracting(Room::getName)
                .containsExactly("Sala 2");
    }
}
