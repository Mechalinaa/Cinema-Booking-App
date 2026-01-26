package com.example.cinema_booking.controllers;

import com.example.cinema_booking.models.Movie;
import com.example.cinema_booking.models.Room;
import com.example.cinema_booking.models.Showtime;
import com.example.cinema_booking.repositories.MovieRepository;
import com.example.cinema_booking.repositories.RoomRepository;
import com.example.cinema_booking.repositories.ShowtimeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AdminControllerITest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @BeforeEach
    void cleanDb() {
        showtimeRepository.deleteAll();
        movieRepository.deleteAll();
        roomRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void getAdminPage_shouldReturnAdminView() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"))
                .andExpect(model().attributeExists("pageTitle"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void addMovie_shouldSaveMovieAndRedirect() throws Exception {
        mockMvc.perform(post("/admin/addMovie")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "Titanic")
                        .param("genre", "Drama")
                        .param("ageRestriction", "13")
                        .param("description", "Film o Titanicu")
                        .param("director", "James Cameron")
                        .param("durationInMinutes", "195")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movies"));

        List<Movie> movies = movieRepository.findAll();
        assertThat(movies).hasSize(1);
        assertThat(movies.get(0).getTitle()).isEqualTo("Titanic");
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    @Transactional
    void updateMovie_shouldPreserveCollections() throws Exception {
        Movie movie = new Movie();
        movie.setTitle("Inception");
        movie.setGenre("Sci-Fi");
        movie.setAgeRestriction(12);
        movie.setDescription("Film o snach");
        movie.setDirector("Nolan");
        movie.setDurationInMinutes(148);
        movie.setActors(new ArrayList<>(List.of("Leonardo DiCaprio")));
        movie.setTrailers(new ArrayList<>(List.of("trailer1")));
        movie.setGallery(new ArrayList<>(List.of("img1")));
        movie = movieRepository.save(movie);

        movie.setTitle("Inception Updated");

        mockMvc.perform(post("/admin/updateMovie")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", movie.getId().toString())
                        .param("title", movie.getTitle())
                        .param("genre", movie.getGenre())
                        .param("ageRestriction", String.valueOf(movie.getAgeRestriction()))
                        .param("description", movie.getDescription())
                        .param("director", movie.getDirector())
                        .param("durationInMinutes", String.valueOf(movie.getDurationInMinutes()))
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movies"));

        Movie updated = movieRepository.findById(movie.getId()).orElseThrow();
        assertThat(updated.getActors()).containsExactly("Leonardo DiCaprio");
        assertThat(updated.getTrailers()).containsExactly("trailer1");
        assertThat(updated.getGallery()).containsExactly("img1");
        assertThat(updated.getTitle()).isEqualTo("Inception Updated");
    }


    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void deleteMovie_shouldRemoveMovie() throws Exception {
        Movie movie = new Movie();
        movie.setTitle("Avatar");
        movie.setGenre("Sci-Fi");
        movie.setAgeRestriction(13);
        movie.setDescription("Film o Pandorze");
        movie.setDirector("James Cameron");
        movie.setDurationInMinutes(162);
        movie = movieRepository.save(movie);

        mockMvc.perform(get("/admin/deleteMovie/" + movie.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movies"));

        assertThat(movieRepository.existsById(movie.getId())).isFalse();
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void addShowtime_shouldSaveShowtime() throws Exception {
        Movie movie = new Movie();
        movie.setTitle("Matrix");
        movie.setGenre("Sci-Fi");
        movie.setAgeRestriction(15);
        movie.setDescription("Neo i Matrix");
        movie.setDirector("Wachowski");
        movie.setDurationInMinutes(136);
        movie = movieRepository.save(movie);

        Room room = new Room();
        room.setName("Sala 1");
        room = roomRepository.save(room);

        mockMvc.perform(post("/admin/addShowtime")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("movie.id", movie.getId().toString())
                        .param("room.id", room.getId().toString())
                        .param("startTime", LocalDateTime.now().toString())
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/showtimes"));

        List<Showtime> showtimes = showtimeRepository.findAll();
        assertThat(showtimes).hasSize(1);
        assertThat(showtimes.get(0).getMovie().getTitle()).isEqualTo("Matrix");
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void deleteShowtime_shouldRemoveShowtime() throws Exception {
        Movie movie = new Movie();
        movie.setTitle("Interstellar");
        movie.setGenre("Sci-Fi");
        movie.setAgeRestriction(12);
        movie.setDescription("Podróż w kosmos");
        movie.setDirector("Nolan");
        movie.setDurationInMinutes(169);
        movie = movieRepository.save(movie);

        Room room = new Room();
        room.setName("Sala 2");
        room = roomRepository.save(room);

        Showtime showtime = new Showtime();
        showtime.setMovie(movie);
        showtime.setRoom(room);
        showtime.setStartTime(LocalDateTime.now());
        showtime = showtimeRepository.save(showtime);

        mockMvc.perform(get("/admin/deleteShowtime/" + showtime.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/showtimes"));

        assertThat(showtimeRepository.existsById(showtime.getId())).isFalse();
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void addMovieForm_shouldReturnAddMovieView() throws Exception {
        mockMvc.perform(get("/admin/addMovie"))
                .andExpect(status().isOk())
                .andExpect(view().name("addMovie"))
                .andExpect(model().attributeExists("pageTitle"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void updateMovieList_shouldReturnViewWithMovies() throws Exception {
        mockMvc.perform(get("/admin/updateMovie"))
                .andExpect(status().isOk())
                .andExpect(view().name("updateMovieList"))
                .andExpect(model().attributeExists("movies"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void editMovieForm_shouldReturnEditView() throws Exception {
        Movie movie = new Movie();
        movie.setTitle("Test");
        movie.setGenre("Test");
        movie.setDirector("Test Director");
        movie.setDescription("Opis");
        movie.setAgeRestriction(12);
        movie.setDurationInMinutes(120);

        movie = movieRepository.save(movie);

        mockMvc.perform(get("/admin/updateMovie/" + movie.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("updateMovieForm"))
                .andExpect(model().attributeExists("movie"))
                .andExpect(model().attribute("movie",
                        hasProperty("id", is(movie.getId()))
                ));
    }



    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void deleteMovieList_shouldReturnView() throws Exception {
        mockMvc.perform(get("/admin/deleteMovie"))
                .andExpect(status().isOk())
                .andExpect(view().name("deleteMovieList"))
                .andExpect(model().attributeExists("movies"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void addShowtimeForm_shouldReturnView() throws Exception {
        mockMvc.perform(get("/admin/addShowtime"))
                .andExpect(status().isOk())
                .andExpect(view().name("addShowtime"))
                .andExpect(model().attributeExists("movies"))
                .andExpect(model().attributeExists("rooms"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void showtimeList_shouldReturnView() throws Exception {
        mockMvc.perform(get("/admin/deleteShowtime"))
                .andExpect(status().isOk())
                .andExpect(view().name("deleteShowtimeList"))
                .andExpect(model().attributeExists("showtimes"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void addShowtime_shouldReturnFormOnValidationError() throws Exception {
        mockMvc.perform(post("/admin/addShowtime")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().isOk())
                .andExpect(view().name("addShowtime"))
                .andExpect(model().attributeExists("error"));
    }

}
