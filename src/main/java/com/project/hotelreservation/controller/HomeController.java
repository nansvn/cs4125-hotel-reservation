package com.project.hotelreservation.controller;

import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.service.RoomService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


/**
 * @author Nan
 */
@Controller
@AllArgsConstructor
public class HomeController {
    private RoomService roomService;

    // initial page if user not logged in
    @GetMapping("/")
    public String showLogin() {
        return "login";
    }

    // redirect users to different page according to their role
    @GetMapping("/home")
    public String showHome(Model model, HttpSession session) {
        if (session.getAttribute("customer") != null) {
            List<Room> rooms = roomService.getAllRooms();
            model.addAttribute("rooms", rooms);
            return "customer/home";
        }

        if (session.getAttribute("admin") != null) {
            model.addAttribute("listRooms", roomService.getAllRooms());
            return "admin/admin";
        }

        model.addAttribute("accessDeniedMessage", "Please log in first");
        return "login";
    }
}
