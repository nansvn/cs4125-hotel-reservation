package com.project.hotelreservation.controller;

import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.service.RoomService;
import com.project.hotelreservation.service.serviceImpl.RoomAvailabilitySubject;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import java.util.Collections;
import java.util.Date;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class CustomerRoomControllerTest {

    @InjectMocks
    private CustomerRoomController customerRoomController;

    @Mock
    private RoomService roomService;

    @Mock
    private RoomAvailabilitySubject roomAvailabilitySubject;

    @Test
    public void testShowSearchResult() {
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);
        when(roomService.searchRoom(any(), any(), anyInt())).thenReturn(Collections.emptyList());

        String viewName = customerRoomController.showSearchResult(new Date(), new Date(), 3, session, model);

        assertEquals("customer/room", viewName);
        verify(model).addAttribute(eq("rooms"), any());
    }

    @Test
    public void testBookRoom() {
        Integer roomId = 1;
        Date checkInDate = new Date();
        Date checkOutDate = new Date();

        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);

        when(session.getAttribute("checkInDate")).thenReturn(checkInDate);
        when(session.getAttribute("checkOutDate")).thenReturn(checkOutDate);

        Room mockRoom = new Room();
        mockRoom.setRoomId(roomId);
        when(roomService.getRoomById(roomId)).thenReturn(mockRoom);

        String viewName = customerRoomController.bookRoom(roomId, session, model);

        assertEquals("customer/booking", viewName);
        verify(session).setAttribute(eq("room"), any(Room.class));
        verify(model).addAttribute("room", mockRoom);

        assertTrue(mockRoom.isAvailable());

    }
}


