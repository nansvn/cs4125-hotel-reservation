package com.project.hotelreservation.service.serviceImpl;

import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.repository.RoomRepository;
import com.project.hotelreservation.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}
