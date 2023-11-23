package com.project.hotelreservation.controller;

import com.project.hotelreservation.model.entity.Customer;
import com.project.hotelreservation.service.AdminService;
import com.project.hotelreservation.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private CustomerService customerService;

    @Mock
    private AdminService adminService;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @Test
    public void testShowLoginPage() {
        String viewName = loginController.showLoginPage();
        assertEquals("login", viewName);
    }

    @Test
    public void testLogin_Customer() {
        String username = "customer";
        String password = "password";
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(password);

        when(customerService.findByUsername(username)).thenReturn(Optional.of(customer));

        String result = loginController.login(username, password, session, model);

        assertEquals("redirect:/home", result);
        verify(session).setAttribute("customer", customer);
    }

    @Test
    public void testLogout() {
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        String result = loginController.logout(model, session);
        Mockito.verify(session).invalidate();
        assertEquals("login", result);
    }
}
