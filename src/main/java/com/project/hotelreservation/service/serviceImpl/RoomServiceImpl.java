package com.project.hotelreservation.service.serviceImpl;

import com.project.hotelreservation.model.entity.Booking;
import com.project.hotelreservation.enums.BedSize;
import com.project.hotelreservation.enums.RoomType;
import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.repository.BookingRepository;
import com.project.hotelreservation.repository.RoomRepository;
import com.project.hotelreservation.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
                bookings.stream().anyMatch(reservation -> {
                    Room reservationRoom = reservation.getRoom();
                    if (reservationRoom != null) {
                        return reservationRoom.equals(room);
                    }
                    return false; // Handle the case where reservationRoom is null
                })
        );

        return availableRooms;
    }

    @Override
    public void addRoom(Integer roomNumber, BigDecimal pricePerNight, Integer maxPeople, boolean available, BedSize bedSize, RoomType roomType, String description, String imagePath) {
        Room room = new Room();
        room.setRoomNumber(roomNumber);
        room.setPricePerNight(pricePerNight);
        room.setMaxPeople(maxPeople);
        room.setAvailable(true); // Assuming all newly added rooms are available
        room.setBedSize(bedSize);
        room.setRoomType(roomType);
        room.setDescription(description);
        room.setImagePath(imagePath);
        //store the room in the repository
        roomRepository.save(room);
    }

    @Override
    public Room getRoomById(Integer roomId) {
        Optional<Room> optional = roomRepository.findById(roomId);
        Room room = null;

        //we get the room from optional
        if(optional.isPresent()){
            room = optional.get();
        }else{
            throw new RuntimeException("Room not found for id ::" + roomId);
        }
        return room;

    }

    @Override
    public void saveRoom(Room room) {
        this.roomRepository.save(room);
    }

    @Override
    public void deleteRoomById(Integer roomId) {
        this.roomRepository.deleteById(roomId);
    }

    @Override
    public List<Room> searchRoomsByRoomId(Integer roomId) {
        return roomRepository.findByRoomId(roomId);
    }


}

