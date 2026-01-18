package com.example.cinema_booking.services;

import com.example.cinema_booking.models.Movie;
import com.example.cinema_booking.models.Showtime;
import com.example.cinema_booking.repositories.ShowtimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShowtimeService {
    private final ShowtimeRepository showRepository;

    @Transactional(readOnly = true)
    public Showtime findById(UUID id) {
        return showRepository.findByIdOrElseThrow(id);
    }

    @Transactional
    public List<Showtime> findAll() {
        return showRepository.findAll();
    }


    @Transactional
    public Showtime save(Showtime newShowtime) {
        validateShowtime(newShowtime);
        return showRepository.save(newShowtime);
    }

    private void validateShowtime(Showtime newShow) {

        if (newShow.getRoom() == null || newShow.getMovie() == null) {
            throw new IllegalArgumentException("Seans musi mieć przypisany film i salę");
        }

        Movie movie = newShow.getMovie();
        LocalDateTime newStart = newShow.getStartTime();
        LocalDateTime newEnd = newStart.plusMinutes(movie.getDurationInMinutes());

        List<Showtime> existingShows =
                showRepository.findByRoom(newShow.getRoom());

        for (Showtime existing : existingShows) {

            if (newShow.getId() != null &&
                    newShow.getId().equals(existing.getId())) {
                continue;
            }

            LocalDateTime existingStart = existing.getStartTime();
            LocalDateTime existingEnd =
                    existingStart.plusMinutes(
                            existing.getMovie().getDurationInMinutes()
                    );

            boolean overlap =
                    newStart.isBefore(existingEnd) &&
                            newEnd.isAfter(existingStart);

            if (overlap) {
                throw new IllegalArgumentException(
                        "Kolizja seansów w tej sali (" +
                                existingStart + " – " + existingEnd + ")"
                );
            }
        }
    }

    @Transactional
    public void deleteById(UUID id) {
        showRepository.deleteById(id);
    }
}
