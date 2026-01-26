package com.example.cinema_booking.controllers;

import com.example.cinema_booking.models.Seat;
import com.example.cinema_booking.models.Showtime;
import com.example.cinema_booking.services.MovieService;
import com.example.cinema_booking.services.RoomService;
import com.example.cinema_booking.services.ShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ShowtimeViewController {
    private final ShowtimeService showtimeService;
    private final MovieService movieService;
    private final RoomService roomService;

    @GetMapping("/showtimes")
    public String shows(Model model) {
        model.addAttribute("showtimes", showtimeService.findAll());
        model.addAttribute("pageTitle", "Seanse");
        return "showtimes";
    }

    @GetMapping("/showtimes/{id}")
    public String showDetails(@PathVariable UUID id, Model model) {
        Showtime show = showtimeService.findById(id);

        Map<Integer, List<Seat>> seatsByRow = new LinkedHashMap<>();
        if (show.getRoom() != null && show.getRoom().getSeats() != null) {
            seatsByRow = show.getRoom().getSeats().stream()
                    .sorted(Comparator.comparingInt(Seat::getRowNum).thenComparingInt(Seat::getSeatNum))
                    .collect(Collectors.groupingBy(
                            Seat::getRowNum,
                            LinkedHashMap::new,
                            Collectors.toList()
                    ));
        }

        model.addAttribute("show", show);
        model.addAttribute("seatsByRow", seatsByRow);
        model.addAttribute("pageTitle", "Seans");
        return "showDetails";
    }
}
