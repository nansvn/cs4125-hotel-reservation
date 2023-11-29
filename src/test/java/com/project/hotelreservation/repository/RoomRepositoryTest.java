package com.project.hotelreservation.repository;

import com.project.hotelreservation.enums.BedSize;
import com.project.hotelreservation.enums.RoomType;
import com.project.hotelreservation.model.entity.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomRepositoryTest {

    @Mock
    private RoomRepository roomRepository;

    private Room room;

    @BeforeEach
    public void setUp() {
        // Set up common data before each test
        room = new Room();
        room.setRoomId(1);
        room.setRoomNumber(101);
        room.setPricePerNight(BigDecimal.valueOf(150));
        room.setMaxPeople(2);
        room.setAvailable(true);
        room.setBedSize(BedSize.DOUBLE);
        room.setRoomType(RoomType.STANDARD);
        room.setDescription("Standard room with queen bed");
        room.setImagePath("image.jpg");
    }

    @Test
    public void testFindRoomsByAvailableAndMaxPeopleGreaterThanEqual() {
        boolean available = true;
        int maxPeople = 2;

        List<Room> rooms = Collections.singletonList(room);

        // Mock the repository method;
        when(roomRepository.findRoomsByAvailableAndMaxPeopleGreaterThanEqual(available, maxPeople))
                .thenReturn(rooms);

        // Call the method
        List<Room> result = roomRepository.findRoomsByAvailableAndMaxPeopleGreaterThanEqual(available, maxPeople);

        // Verify the result
        assertEquals(rooms, result);

        // Verify that the method was called with the correct arguments
        verify(roomRepository, times(1)).findRoomsByAvailableAndMaxPeopleGreaterThanEqual(available, maxPeople);
    }

    @Test
    public void testFindByRoomId() {
        Integer roomId = 1;

        // Mock the repository method;
        when(roomRepository.findByRoomId(roomId))
                .thenReturn(Collections.singletonList(room));

        // Call the method
        List<Room> result = roomRepository.findByRoomId(roomId);

        // Verify the result
        assertEquals(Collections.singletonList(room), result);

        // Verify that the method was called with the correct argument
        verify(roomRepository, times(1)).findByRoomId(roomId);
    }
}
