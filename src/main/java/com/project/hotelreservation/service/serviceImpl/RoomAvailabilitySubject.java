package com.project.hotelreservation.service.serviceImpl;

import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.service.RoomAvailabilityObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoomAvailabilitySubject {
    private List<RoomAvailabilityObserver> observers = new ArrayList<>();
    private Room room;
    @Autowired
    public RoomAvailabilitySubject(List<RoomAvailabilityObserver> observers) {
        this.observers.addAll(observers);
    }

    public void addObserver(RoomAvailabilityObserver observer) {

        observers.add(observer);
    }

    public void removeObserver(RoomAvailabilityObserver observer) {

        observers.remove(observer);
    }

    public void setRoom(Room room) {
        this.room = room;
        notifyObservers();
    }

    public Room getRoom() {

        return room;
    }

    public void notifyObservers() {
        for (RoomAvailabilityObserver observer : observers) {
            observer.update(room);
        }
    }
}
