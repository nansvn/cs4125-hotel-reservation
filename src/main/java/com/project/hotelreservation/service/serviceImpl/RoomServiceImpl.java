package com.project.hotelreservation.service.serviceImpl;

import com.project.hotelreservation.model.entity.Booking;
import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.repository.BookingRepository;
import com.project.hotelreservation.repository.RoomRepository;
import com.project.hotelreservation.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {
    private RoomRepository roomRepository;
    private BookingRepository bookingRepository;

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> searchRoom(Date inDate, Date outDate, int people) {
        List<Booking> bookings = bookingRepository
                .findBookingByCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(inDate, outDate);

        List<Room> availableRooms = roomRepository
                .findRoomsByAvailableAndMaxPeopleGreaterThanEqual(true, people);
        availableRooms.removeIf(room ->
                bookings.stream().anyMatch(
                        reservation -> reservation.getRoom().equals(room)
                )
        );
        return availableRooms;
    }
}
