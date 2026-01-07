package com.example.cinema_booking.web;

import com.example.cinema_booking.model.Seat;
import com.example.cinema_booking.model.Show;
import com.example.cinema_booking.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ShowViewController {
    private final ShowService showService;

    @GetMapping("/shows")
    public String showsPage(Model model) {
        model.addAttribute("shows", showService.findAll());
        return "shows";
    }

    @GetMapping("/shows/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
        Show show = showService.findById(id);

        List<List<Seat>> seatRows = new ArrayList<>();
        for (int row = 1; row <= show.getRoom().getRows(); row++) {
            int currentRow = row;
            List<Seat> seatsInRow = show.getRoom().getSeats().stream()
                    .filter(s -> s.getRowNum() == currentRow)
                    .toList();
            seatRows.add(seatsInRow);
        }

        model.addAttribute("show", show);
        model.addAttribute("seatRows", seatRows);

        return "show-details";
    }
}
