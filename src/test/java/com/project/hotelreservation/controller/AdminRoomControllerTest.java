package com.project.hotelreservation.controller;

import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.service.RoomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import java.util.Collections;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class AdminRoomControllerTest {

    @InjectMocks
    private AdminRoomController adminRoomController;

    @Mock
    private RoomService roomService;

    @Test
    public void testShowAddRoomForm() {
        String viewName = adminRoomController.showAddRoomForm();
        assertEquals("admin/new-room", viewName);
    }

    @Test
    public void testShowRoomFormForUpdate() {
        Model model = mock(Model.class);
        when(roomService.getRoomById(1)).thenReturn(new Room());

        String viewName = adminRoomController.showRoomFormForUpdate(1, model);

        assertEquals("admin/update-room", viewName);
        verify(model).addAttribute(eq("room"), any());
    }

    @Test
    public void testSaveRoom() {
        Room room = new Room();
        String viewName = adminRoomController.saveRoom(room);
        assertEquals("redirect:/admin", viewName);
        verify(roomService).saveRoom(room);
    }

    @Test
    public void testDeleteRoom() {
        String viewName = adminRoomController.deleteRoom(1);
        assertEquals("redirect:/admin", viewName);
        verify(roomService).deleteRoomById(1);
    }

    @Test
    public void testSearchARoom() {
        Model model = mock(Model.class);
        when(roomService.searchRoomsByRoomId(1)).thenReturn(Collections.singletonList(new Room()));

        String viewName = adminRoomController.searchARoom(1, model);

        assertEquals("admin/list-rooms", viewName);
        verify(model).addAttribute(eq("listRooms"), any());
    }
}


