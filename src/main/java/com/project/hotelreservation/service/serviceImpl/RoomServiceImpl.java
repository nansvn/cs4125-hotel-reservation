package com.project.hotelreservation.service.serviceImpl;

import com.project.hotelreservation.model.entity.Booking;
import com.project.hotelreservation.enums.BedSize;
import com.project.hotelreservation.enums.RoomType;
import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.repository.BookingRepository;
import com.project.hotelreservation.repository.RoomRepository;
import com.project.hotelreservation.service.RoomFactory;
import com.project.hotelreservation.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private RoomFactory roomFactory;
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

    @Override
    public Room addRoom(Integer roomNumber, BigDecimal pricePerNight, Integer maxPeople, boolean available, BedSize bedSize, RoomType roomType, String description, String imagePath) {
        Room room = roomFactory.createRoom(roomNumber, pricePerNight, maxPeople, available, bedSize, roomType, description, imagePath);
        room.setAvailable(true);
        //store the room in the repository
        room = roomRepository.save(room);
        return room;
    }

}
