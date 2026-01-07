package com.example.cinema_booking.web;

import com.example.cinema_booking.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MovieViewController { //do serwisu dodajemy repo, a do controllera serwis
    private final MovieService movieService;

    @GetMapping("/movies")
    public String moviesPage(Model model){
        model.addAttribute("movies", movieService.findAll());
        return "movies";
    }
}
