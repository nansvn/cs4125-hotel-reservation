package com.project.hotelreservation.service;

import com.project.hotelreservation.model.entity.Room;

public interface RoomAvailabilityObserver {
    void update(Room room);
}
