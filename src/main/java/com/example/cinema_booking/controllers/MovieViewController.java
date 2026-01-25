package com.example.cinema_booking.controllers;

import com.example.cinema_booking.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MovieViewController {
    private final MovieService movieService;

    @GetMapping("/movies")
    public String moviesPage(Model model){
        model.addAttribute("movies", movieService.findAll());
        return "movies";
    }


}
