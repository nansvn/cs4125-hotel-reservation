package com.project.hotelreservation.repository;

import com.project.hotelreservation.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findRoomsByAvailableAndMaxPeopleGreaterThanEqual(boolean available, int maxPeople);
}
