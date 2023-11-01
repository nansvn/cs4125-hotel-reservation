package com.project.hotelreservation.service.serviceImpl;

import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToRoomConverter implements Converter<String, Room> {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room convert(String roomId) {
        Integer id = Integer.parseInt(roomId);
        return roomRepository.findById(id).orElse(null);
    }
}
