package com.example.cinema_booking.repositories;

import com.example.cinema_booking.models.ReservedSeat;
import com.example.cinema_booking.models.Seat;
import com.example.cinema_booking.models.Room;
import com.example.cinema_booking.models.Showtime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jakarta.persistence.EntityExistsException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class ReservedSeatRepositoryTest {

    @Autowired
    private ReservedSeatRepository reservedSeatRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    private Room room;
    private Seat seat1;
    private Seat seat2;
    private Showtime showtime;

    @BeforeEach
    void setUp() {
        reservedSeatRepository.deleteAll();
        showtimeRepository.deleteAll();
        seatRepository.deleteAll();
        roomRepository.deleteAll();

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

        showtime = new Showtime();
        showtime.setRoom(room);
        showtime.setStartTime(LocalDateTime.now().withNano(0));
        showtimeRepository.save(showtime);
    }

    @Test
    void shouldSaveReservedSeat() {
        ReservedSeat reservedSeat = new ReservedSeat();
        reservedSeat.setSeat(seat1);
        reservedSeat.setShowtime(showtime);

        ReservedSeat saved = reservedSeatRepository.save(reservedSeat);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getSeat()).isEqualTo(seat1);
        assertThat(saved.getShowtime()).isEqualTo(showtime);
    }

    @Test
    void shouldFindByShowtime() {
        ReservedSeat rs1 = new ReservedSeat();
        rs1.setSeat(seat1);
        rs1.setShowtime(showtime);

        ReservedSeat rs2 = new ReservedSeat();
        rs2.setSeat(seat2);
        rs2.setShowtime(showtime);

        reservedSeatRepository.saveAll(List.of(rs1, rs2));

        List<ReservedSeat> seats = reservedSeatRepository.findByShowtime(showtime);

        assertThat(seats).hasSize(2)
                .extracting(ReservedSeat::getSeat)
                .containsExactlyInAnyOrder(seat1, seat2);
    }

    @Test
    void shouldCheckExistsByShowtimeAndSeat() {
        ReservedSeat rs = new ReservedSeat();
        rs.setSeat(seat1);
        rs.setShowtime(showtime);
        reservedSeatRepository.save(rs);

        boolean exists = reservedSeatRepository.existsByShowtimeAndSeat(showtime, seat1);
        boolean notExists = reservedSeatRepository.existsByShowtimeAndSeat(showtime, seat2);

        assertThat(exists).isTrue();
        assertThat(notExists).isFalse();
    }

    @Test
    void shouldEnforceUniqueConstraint() {
        ReservedSeat rs1 = new ReservedSeat();
        rs1.setSeat(seat1);
        rs1.setShowtime(showtime);
        reservedSeatRepository.save(rs1);

        ReservedSeat rs2 = new ReservedSeat();
        rs2.setSeat(seat1);
        rs2.setShowtime(showtime);

        assertThatThrownBy(() -> reservedSeatRepository.saveAndFlush(rs2))
                .isInstanceOfAny(org.springframework.dao.DataIntegrityViolationException.class);
    }

}
