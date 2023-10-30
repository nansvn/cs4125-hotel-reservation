package com.project.hotelreservation.service;

import com.project.hotelreservation.model.entity.Room;

import java.util.Date;
import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();
    List<Room> searchRoom(Date inDate, Date outDate, int people);
}



