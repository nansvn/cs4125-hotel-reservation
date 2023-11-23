package com.project.hotelreservation.controller;

import com.project.hotelreservation.model.WebCustomer;
import com.project.hotelreservation.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;
import java.util.logging.Logger;

@ExtendWith(SpringExtension.class)
public class RegisterControllerTest {

    @InjectMocks
    private RegisterController registerController;

    @Mock
    private CustomerService customerService;

    @Mock
    private Logger logger;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @Test
    public void testShowRegisterPage() {
        String viewName = registerController.showRegisterPage(model);
        assertEquals("register", viewName);
        verify(model).addAttribute(eq("webCustomer"), any(WebCustomer.class));
    }

    @Test
    public void testRegister_Success() {
        WebCustomer webCustomer = new WebCustomer();
        webCustomer.setUsername("username");

        when(customerService.findByUsername(webCustomer.getUsername())).thenReturn(Optional.empty());

        String result = registerController.register(webCustomer, mock(BindingResult.class), session, model);

        assertEquals("login", result);
        verify(customerService).save(webCustomer);
        verify(session).setAttribute("customer", webCustomer);
    }
}

