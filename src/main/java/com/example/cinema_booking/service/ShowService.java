package com.example.cinema_booking.service;

import com.example.cinema_booking.model.Show;
import com.example.cinema_booking.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ShowService {
    private final ShowRepository showRepository;

    public Show save(Show show){
        return showRepository.save(show);
    }

    public List<Show> findAll(){
        return showRepository.findAll();
    }

    public Show findById(Long id){
        return showRepository.findById(id).orElse(null);
    }
}
