package com.example.cinema_booking.service;

import com.example.cinema_booking.model.Room;
import com.example.cinema_booking.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public Room save(Room room){
        return roomRepository.save(room);
    }

    public List<Room> findAll(){
        return roomRepository.findAll();
    }

    public Room findById(Long id){
        return roomRepository.findById(id).orElse(null);
    }
}
