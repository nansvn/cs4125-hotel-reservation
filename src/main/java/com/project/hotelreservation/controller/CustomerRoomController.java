package com.project.hotelreservation.controller;

import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.service.RoomService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
        session.setAttribute("checkOutDate", checkOutDate);
        session.setAttribute("people", people);
        return "customer/room";
    }

    @GetMapping("/book/{roomId}")
    public String bookRoom(@PathVariable("roomId") Integer roomId, HttpSession session, Model model) {
        Room room = roomService.getRoomById(roomId);
        session.setAttribute("room", room);

        // check in and check out time should be modified
        // set check in time to 12am and check out time to 9am
        Date checkInDate = (Date) session.getAttribute("checkInDate");
        // convert to LocalDateTime item
        LocalDateTime localCheckInDate = LocalDateTime.ofInstant(checkInDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime updatedCheckInDate = localCheckInDate.plusHours(12); // add 12 hours
        // convert back
        Date newCheckInDate = Date.from(updatedCheckInDate.atZone(ZoneId.systemDefault()).toInstant());

        Date checkOutDate = (Date) session.getAttribute("checkOutDate");
        // convert to LocalDateTime item
        LocalDateTime localCheckOutDate = LocalDateTime.ofInstant(checkOutDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime updatedCheckOutDate = localCheckOutDate.plusHours(9); // add 12 hours
        // convert back
        Date newCheckOutDate = Date.from(updatedCheckOutDate.atZone(ZoneId.systemDefault()).toInstant());

        model.addAttribute("room", room);
        model.addAttribute("checkInDate", newCheckInDate);
        model.addAttribute("checkOutDate", newCheckOutDate);
        model.addAttribute("people", session.getAttribute("people"));
        return "customer/booking";
    }
}
