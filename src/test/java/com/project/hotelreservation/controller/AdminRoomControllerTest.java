package com.project.hotelreservation.controller;
import com.project.hotelreservation.service.RoomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdminRoomController.class)
public class AdminRoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Test
    public void testViewRoomPage()
            throws Exception {
    }

    @Test
    public void testViewRoomPage_NoRoomsAvailable()
            throws Exception {
    }

    @Test
    public void testShowAddRoomForm()
            throws Exception {
    }

    @Test
    public void testAddRoom_Success()
            throws Exception {
    }

    @Test
    public void testAddRoom_InvalidInput()
            throws Exception {
    }

    @Test
    public void testShowRoomFormForUpdate()
            throws Exception {
    }
    @Test
    public void testSaveRoom()
            throws Exception {
    }

    @Test
    public void testDeleteRoom()
            throws Exception {
    }

    @Test
    public void testSearchARoom()
            throws Exception {
    }
}

