package com.project.hotelreservation.service;

import com.project.hotelreservation.enums.BedSize;
import com.project.hotelreservation.enums.RoomType;
import com.project.hotelreservation.model.entity.Room;

import java.math.BigDecimal;

public interface RoomFactory {
    Room createRoom(Integer roomNumber, BigDecimal pricePerNight, Integer maxPeople, boolean available, BedSize bedSize, RoomType roomType, String description, String imagePath);
}
