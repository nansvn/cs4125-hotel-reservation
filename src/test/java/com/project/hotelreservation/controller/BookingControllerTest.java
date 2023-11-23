package com.project.hotelreservation.controller;

import com.project.hotelreservation.service.AdditionalServicesService;
import com.project.hotelreservation.service.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @MockBean
    private AdditionalServicesService additionalServicesService;


    @Test
    public void testShowBookingPage()
            throws Exception {
    }

    @Test
    public void testProcessBooking_NoServicesSelected()
            throws Exception {
    }

    @Test
    public void testProcessBooking_WithServicesSelected()
            throws Exception {
    }

    @Test
    public void testViewOrders_NoOrders()
            throws Exception {
    }

    @Test
    public void testViewOrders_WithOrders()
            throws Exception {
    }
}
