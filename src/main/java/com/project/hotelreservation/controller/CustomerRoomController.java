package com.project.hotelreservation.controller;

import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerRoomController {
    //Display list of rooms
    @Autowired
    private RoomService roomService;
    @GetMapping("/room")
    public String viewRoomPage(Model model){
        model.addAttribute("listRooms",roomService.getAllRooms());
    return "room";
    }

}
