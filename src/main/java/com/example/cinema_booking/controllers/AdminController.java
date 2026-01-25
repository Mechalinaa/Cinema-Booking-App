package com.example.cinema_booking.controllers;

import com.example.cinema_booking.models.Movie;
import com.example.cinema_booking.models.Showtime;
import com.example.cinema_booking.services.MovieService;
import com.example.cinema_booking.services.RoomService;
import com.example.cinema_booking.services.ShowtimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final MovieService movieService;
    private final RoomService roomService;
    private final ShowtimeService showtimeService;

    @GetMapping()
    public String admin(){
        return "admin";
    }

    @GetMapping("/addMovie")
    public String addMovieForm(@ModelAttribute("movie") Movie movie) {
        return "addMovie";
    }

    @PostMapping("/addMovie")
    public String addMovieSubmit(@ModelAttribute Movie movie) {

        movieService.save(movie);
        return "redirect:/movies";
    }

    @GetMapping("/updateMovie")
    public String updateMovie(Model model){
        model.addAttribute("movies", movieService.findAll());
        return "updateMovieList";
    }

    @GetMapping("/updateMovie/{id}")
    public String editMovieForm(@PathVariable UUID id, Model model) {
        Movie movie = movieService.findById(id);
        model.addAttribute("movie", movie);
        return "updateMovieForm";
    }

    @PostMapping("/updateMovie")
    public String updateMovieSubmit(@ModelAttribute Movie movie) {
        Movie original = movieService.findById(movie.getId());

        movie.setActors(original.getActors());
        movie.setTrailers(original.getTrailers());
        movie.setGallery(original.getGallery());

        movieService.save(movie);
        return "redirect:/movies";
    }

    @GetMapping("/deleteMovie")
    public String deleteMovie(Model model){
        model.addAttribute("movies", movieService.findAll());
        return "deleteMovieList";
    }

    @GetMapping("/deleteMovie/{id}")
    public String deleteMovieForm(@PathVariable UUID id){
        movieService.deleteById(id);
        return "redirect:/movies";
    }

    @GetMapping("/addShowtime")
    public String addShowtimeForm(Model model) {
        model.addAttribute("showtime", new Showtime());
        model.addAttribute("movies", movieService.findAll());
        model.addAttribute("rooms", roomService.findAll());
        return "addShowtime";
    }

    @PostMapping("/addShowtime")
    public String saveShowtime(@ModelAttribute("showtime") Showtime showtime, Model model) {
        try {
            showtimeService.save(showtime);
            return "redirect:/showtimes";
        }
        catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("movies", movieService.findAll());
            model.addAttribute("rooms", roomService.findAll());
            return "addShowtime";
        }
    }

    @GetMapping("/deleteShowtime")
    public String showtimeList(Model model) {
        model.addAttribute("showtimes", showtimeService.findAll());
        return "deleteShowtimeList";
    }

    @GetMapping("/deleteShowtime/{id}")
    public String deleteShowtime(@PathVariable UUID id) {
        showtimeService.deleteById(id);
        return "redirect:/showtimes";
    }
}
