package com.project.hotelreservation.controller;

import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.service.RoomService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {
    private RoomService roomService;
    @GetMapping("/")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/home")
    public String showHome(Model model, HttpSession session) {
        if (session.getAttribute("customer") == null) {
            model.addAttribute("accessDeniedMessage", "Please log in first");
            return "login";
        }
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms",rooms);
        return "home";
    }
}
