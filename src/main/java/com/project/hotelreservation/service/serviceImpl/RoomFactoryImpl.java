package com.project.hotelreservation.service.serviceImpl;

import com.project.hotelreservation.enums.BedSize;
import com.project.hotelreservation.enums.RoomType;
import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.service.RoomFactory;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class RoomFactoryImpl implements RoomFactory {

    @Override
    public Room createRoom(Integer roomNumber, BigDecimal pricePerNight, Integer maxPeople, boolean available, BedSize bedSize, RoomType roomType, String description, String imagePath) {
        Room room = new Room();
        room.setRoomNumber(roomNumber);
        room.setPricePerNight(pricePerNight);
        room.setMaxPeople(maxPeople);
        room.setAvailable(available);
        room.setBedSize(bedSize);
        room.setRoomType(roomType);
        room.setDescription(description);
        room.setImagePath(imagePath);
        return room;
    }
}
