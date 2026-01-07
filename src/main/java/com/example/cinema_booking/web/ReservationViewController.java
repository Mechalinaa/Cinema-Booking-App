package com.example.cinema_booking.web;

import com.example.cinema_booking.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ReservationViewController {
    private final ReservationService reservationService;

    @GetMapping("/reservations")
    public String reservationsPage(Model model){
        model.addAttribute("reservations", reservationService.findAll());
        return "reservations";
    }
}
