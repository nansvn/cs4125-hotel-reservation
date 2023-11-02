package com.project.hotelreservation.controller;

import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.service.RoomService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class CustomerRoomController {
    private RoomService roomService;

    @GetMapping("/search")
    public String showSearchResult(@RequestParam("checkInDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkInDate,
                                   @RequestParam("checkOutDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkOutDate,
                                   @RequestParam("people") int people,
                                   HttpSession session,
                                   Model model) {
        List<Room> rooms = roomService.searchRoom(checkInDate, checkOutDate, people);
        model.addAttribute("rooms", rooms);
        session.setAttribute("checkInDate", checkInDate);
        session.setAttribute("people", checkOutDate);
        session.setAttribute("people", people);
        return "customer/room";
    }
}
