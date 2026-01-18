package com.example.cinema_booking.controllers;

import com.example.cinema_booking.models.Movie;
import com.example.cinema_booking.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MovieViewController {
    private final MovieService movieService;

    @GetMapping("/movies")
    public String moviesPage(Model model){
        model.addAttribute("movies", movieService.findAll());
        return "movies";
    }

    @GetMapping("/admin/addMovie")
    public String addMovieForm(@ModelAttribute("movie") Movie movie) {
        return "addMovie";
    }

    @PostMapping("/admin/addMovie")
    public String addMovieSubmit(@ModelAttribute Movie movie) {

        movieService.save(movie);
        return "redirect:/movies";
    }

    @GetMapping("/admin/updateMovie")
    public String updateMovie(Model model){
        model.addAttribute("movies", movieService.findAll());
        return "updateMovieList"; //lista filmow do wyboru
    }

    @GetMapping("/admin/updateMovie/{id}")
    public String editMovieForm(@PathVariable UUID id, Model model) {
        Movie movie = movieService.findById(id);
        model.addAttribute("movie", movie);
        return "updateMovieForm";
    }

    @PostMapping("/admin/updateMovie")
    public String updateMovieSubmit(@ModelAttribute Movie movie) {
        Movie original = movieService.findById(movie.getId());

        movie.setActors(original.getActors());
        movie.setTrailers(original.getTrailers());
        movie.setGallery(original.getGallery());

        movieService.save(movie);
        return "redirect:/movies";
    }

    @GetMapping("/admin/deleteMovie")
    public String deleteMovie(Model model){
        model.addAttribute("movies", movieService.findAll());
        return "deleteMovieList"; //lista filmow do wyboru
    }

    @GetMapping("/admin/deleteMovie/{id}")
    public String deleteMovieForm(@PathVariable UUID id){
        movieService.deleteById(id);
        return "redirect:/movies";
    }
}
