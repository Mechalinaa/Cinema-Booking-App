package com.example.cinema_booking.controllers;

import com.example.cinema_booking.models.Reservation;
import com.example.cinema_booking.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ReservationViewController {
    private final ReservationService reservationService;

    @GetMapping("/reservation/{id}")
    public String reservationSummary(@PathVariable UUID id, Model model) {
        Reservation reservation = reservationService.findById(id);
        model.addAttribute("reservation", reservation);
        return "reservation";
    }

    @PostMapping("/showtimes/{id}/reservation")
    public String addReservation(@PathVariable UUID id,
                                 @RequestParam(name = "seatIds") List<UUID> seatIds,
                                 Model model) {

        Reservation reservation = reservationService.addNewReservation(id, seatIds);
        model.addAttribute("reservations", reservation);
        return "redirect:/reservation/" + reservation.getId();
    }
}
