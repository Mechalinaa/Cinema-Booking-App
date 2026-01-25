package com.example.cinema_booking.controllers;

import com.example.cinema_booking.models.Movie;
import com.example.cinema_booking.models.Showtime;
import com.example.cinema_booking.repositories.ShowtimeRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final ShowtimeRepository showtimeRepository;

    public HomeController(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    @GetMapping("/")
    public String home(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date,
            Model model
    ) {
        if (date == null) {
            date = LocalDate.now();
        }

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        List<Showtime> showtimes =
                showtimeRepository.findByStartTimeBetween(startOfDay, endOfDay);

        Map<Movie, List<Showtime>> repertoire =
                showtimes.stream()
                        .collect(Collectors.groupingBy(Showtime::getMovie));

        model.addAttribute("selectedDate", date);
        model.addAttribute("repertoire", repertoire);

        return "home";
    }
}

