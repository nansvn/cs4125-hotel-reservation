package com.project.hotelreservation.service;

import com.project.hotelreservation.enums.BedSize;
import com.project.hotelreservation.enums.RoomType;
import com.project.hotelreservation.model.entity.Room;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();
    List<Room> searchRoom(Date inDate, Date outDate, int people);

    Room addRoom(Integer roomNumber, BigDecimal pricePerNight, Integer maxPeople, boolean available, BedSize bedSize, RoomType roomType, String description, String imagePath);
}
