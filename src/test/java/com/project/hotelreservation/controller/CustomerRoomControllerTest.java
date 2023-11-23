package com.project.hotelreservation.controller;

import com.project.hotelreservation.service.CustomerService;
import com.project.hotelreservation.service.RoomService;
import com.project.hotelreservation.service.serviceImpl.RoomAvailabilitySubject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerRoomController.class)
public class CustomerRoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private RoomService roomService;

    @MockBean
    private RoomAvailabilitySubject roomAvailabilitySubject;

    @Test
    public void testShowSearchResult_WithAvailableRooms()
            throws Exception {

    }

    @Test
    public void testShowSearchResult_NoAvailableRooms()
            throws Exception {
    }

    @Test
    public void testBookRoom_Success()
            throws Exception {
    }

    @Test
    public void testBookRoom_InvalidRoom()
            throws Exception {
    }
}

