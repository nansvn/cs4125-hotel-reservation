package com.project.hotelreservation.service;

import com.project.hotelreservation.enums.BedSize;
import com.project.hotelreservation.enums.RoomType;
import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.repository.BookingRepository;
import com.project.hotelreservation.repository.RoomRepository;
import com.project.hotelreservation.service.serviceImpl.RoomServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private RoomServiceImpl roomService;

    @Test
    public void testGetAllRooms() {
        List<Room> rooms = Arrays.asList(new Room(), new Room());

        when(roomRepository.findAll()).thenReturn(rooms);
        List<Room> result = roomService.getAllRooms();

        assertEquals(2, result.size());
    }
    @Test
    public void testAddRoom() {
        Integer roomNumber = 101;
        BigDecimal pricePerNight = new BigDecimal("100.00");
        Integer maxPeople = 2;
        BedSize bedSize = BedSize.KING;
        RoomType roomType = RoomType.DELUXE;
        String description = "Test room";
        String imagePath = "src/main/java/com.project.hotelreservation/resources/static.images";

        roomService.addRoom(roomNumber, pricePerNight, maxPeople, true, bedSize, roomType, description, imagePath);

        ArgumentCaptor<Room> roomCaptor = ArgumentCaptor.forClass(Room.class);
        verify(roomRepository).save(roomCaptor.capture());

        Room capturedRoom = roomCaptor.getValue();

        assertNotNull(capturedRoom);
        assertEquals(roomNumber, capturedRoom.getRoomNumber());
        assertEquals(pricePerNight, capturedRoom.getPricePerNight());
        assertEquals(maxPeople, capturedRoom.getMaxPeople());
        assertEquals(true, capturedRoom.isAvailable());
        assertEquals(bedSize, capturedRoom.getBedSize());
        assertEquals(roomType, capturedRoom.getRoomType());
        assertEquals(description, capturedRoom.getDescription());
        assertEquals(imagePath, capturedRoom.getImagePath());
    }
    @Test
    public void testGetRoomById() {
        Integer roomId = 1;
        Room room = new Room();
        room.setRoomId(roomId);

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        Room result = roomService.getRoomById(roomId);

        assertNotNull(result);
        assertEquals(roomId, result.getRoomId());
    }
}

