package com.project.hotelreservation.controller;

import com.project.hotelreservation.model.entity.Customer;
import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.service.AdditionalServicesService;
import com.project.hotelreservation.service.BookingService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class BookingControllerTest {

    @InjectMocks
    private BookingController bookingController;

    @Mock
    private BookingService bookingService;

    @Mock
    private AdditionalServicesService additionalServicesService;

    @Test
    public void testShowBookingPage() {
        Model model = mock(Model.class);
        String viewName = bookingController.showBookingPage(model);
        assertEquals("customer/booking", viewName);
        verify(model).addAttribute(eq("newBooking"), any());
    }

    @Test
    public void testProcessBooking() {
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        when(additionalServicesService.getAllAdditionalServices()).thenReturn(Collections.emptyList());

        String viewName = bookingController.processBooking(model);

        assertEquals("customer/additional-services", viewName);
        verify(model).addAttribute(eq("additionalServices"), anyList());
    }

    @Test
    public void testConfirmBooking() {
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("room")).thenReturn(new Room());
        when(session.getAttribute("customer")).thenReturn(new Customer());
        when(session.getAttribute("checkInDate")).thenReturn(new Date());
        when(session.getAttribute("checkOutDate")).thenReturn(new Date());
        when(additionalServicesService.getServicesByIds(anyList())).thenReturn(Collections.emptyList());

        String viewName = bookingController.confirmBooking(Collections.singletonList(1), session, model);

        assertEquals("customer/booking-confirmation", viewName);
        verify(bookingService).save(any(), anyList(), any(), any(), any(), eq(false));
        verify(model).addAttribute(eq("room"), any());
    }

    @Test
    public void testViewOrders_CustomerLoggedIn() {
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("customer")).thenReturn(new Customer());
        when(bookingService.getOrdersByCustomer(any())).thenReturn(Collections.emptyList());

        String viewName = bookingController.viewOrders(model, session);

        assertEquals("customer/orders", viewName);
        verify(model).addAttribute(eq("userBookings"), anyList());
    }

    @Test
    public void testViewOrders_NotLoggedIn() {
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("customer")).thenReturn(null);

        String viewName = bookingController.viewOrders(mock(Model.class), session);

        assertEquals("redirect:/login", viewName);
    }
}


