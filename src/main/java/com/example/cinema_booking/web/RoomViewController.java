package com.example.cinema_booking.web;

import com.example.cinema_booking.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class RoomViewController {
    private final RoomService roomService;

    @GetMapping("/rooms")
    public String roomsPage(Model model){
        model.addAttribute("rooms", roomService.findAll());
        return "rooms";
    }
}
