package com.example.cinema_booking.web;
import com.example.cinema_booking.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ShowViewController {
    private final ShowService showService;

    @GetMapping("/shows")
    public String showsPage(Model model){
        model.addAttribute("shows", showService.findAll());
        return "shows";
    }
}
