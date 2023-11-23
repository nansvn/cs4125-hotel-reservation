package com.project.hotelreservation.controller;

import com.project.hotelreservation.service.RoomService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.Model;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

    @Mock
    private RoomService roomService;

    @Test
    public void testShowLogin() {
        String viewName = homeController.showLogin();
        assertEquals("login", viewName);
    }

    @Test
    public void testShowHome_CustomerLoggedIn() {
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);

        when(session.getAttribute("customer")).thenReturn(new Object());

        String viewName = homeController.showHome(model, session);

        assertEquals("customer/home", viewName);
        verify(model).addAttribute(eq("rooms"), anyList());
    }

    @Test
    public void testShowHome_AdminLoggedIn() {
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);

        when(session.getAttribute("admin")).thenReturn(new Object());
        when(roomService.getAllRooms()).thenReturn(Collections.emptyList());

        String viewName = homeController.showHome(model, session);

        assertEquals("admin/admin", viewName);
        verify(model).addAttribute(eq("listRooms"), anyList());
    }

    @Test
    public void testShowHome_NotLoggedIn() {
        HttpSession session = mock(HttpSession.class);
        Model model = mock(Model.class);

        when(session.getAttribute("customer")).thenReturn(null);
        when(session.getAttribute("admin")).thenReturn(null);

        String viewName = homeController.showHome(model, session);

        assertEquals("login", viewName);
        verify(model).addAttribute(eq("accessDeniedMessage"), anyString());
    }
}

