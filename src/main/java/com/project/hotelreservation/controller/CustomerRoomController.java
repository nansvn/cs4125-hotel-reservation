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

/**
 * @author Nan
 */
@Controller
@AllArgsConstructor
public class CustomerRoomController {
    private RoomService roomService;


    // user can search the available rooms with checkin, checkout date and no of ppl
    // return the search result list
    @GetMapping("/search")
    public String showSearchResult(@RequestParam("checkInDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkInDate,
                                   @RequestParam("checkOutDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkOutDate,
                                   @RequestParam("people") int people,
                                   HttpSession session,
                                   Model model) {
        List<Room> rooms = roomService.searchRoom(checkInDate, checkOutDate, people);

        // add room list as mode attribute for display purpose
        model.addAttribute("rooms", rooms);

        // save checkin/out, ppl in session to be used later in other functions
        session.setAttribute("checkInDate", checkInDate);
        session.setAttribute("checkOutDate", checkOutDate);
        session.setAttribute("people", people);

        return "customer/room";
    }

    // after user click the room they want to book from search result page they will be here
    // check in/out time adjusted
    // return the booking info overview page
    @GetMapping("/book/{roomId}")
    public String bookRoom(@PathVariable("roomId") Integer roomId, HttpSession session, Model model) {
        Room room = roomService.getRoomById(roomId);
        session.setAttribute("room", room);

        // set check-in time to 12 pm (noon)
        Date checkInDate = (Date) session.getAttribute("checkInDate");
        LocalDateTime localCheckInDate = LocalDateTime.ofInstant(checkInDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime updatedCheckInDate = localCheckInDate.withHour(12).withMinute(0).withSecond(0).withNano(0);
        Date newCheckInDate = Date.from(updatedCheckInDate.atZone(ZoneId.systemDefault()).toInstant());
        // update in the session
        session.setAttribute("checkInDate", newCheckInDate);

        // set check-out time to 9 am
        Date checkOutDate = (Date) session.getAttribute("checkOutDate");
        LocalDateTime localCheckOutDate = LocalDateTime.ofInstant(checkOutDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime updatedCheckOutDate = localCheckOutDate.withHour(9).withMinute(0).withSecond(0).withNano(0);
        Date newCheckOutDate = Date.from(updatedCheckOutDate.atZone(ZoneId.systemDefault()).toInstant());
        // update in the session
        session.setAttribute("checkOutDate", newCheckOutDate);

        model.addAttribute("room", room);
        model.addAttribute("checkInDate", newCheckInDate);
        model.addAttribute("checkOutDate", newCheckOutDate);
        model.addAttribute("people", session.getAttribute("people"));
        return "customer/booking";
    }
}
