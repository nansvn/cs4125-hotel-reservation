package com.project.hotelreservation.controller;

import com.project.hotelreservation.enums.BedSize;
import com.project.hotelreservation.enums.RoomType;
import com.project.hotelreservation.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@AllArgsConstructor
@Controller
public class AdminRoomController {
    //Display list of rooms
    @Autowired
    private RoomService roomService;
    @GetMapping("/admin")
    public String viewRoomPage(Model model){
        model.addAttribute("listRooms",roomService.getAllRooms());
    return "admin";
    }

    @GetMapping("/new_room")
    public String showAddRoomForm() {
        return "new_room";
    }

    @PostMapping("/new_room")
    public String addRoom(@RequestParam Integer roomNumber,
            @RequestParam BigDecimal pricePerNight,
            @RequestParam Integer maxPeople,
            @RequestParam boolean available,
            @RequestParam BedSize bedSize,
            @RequestParam RoomType roomType,
            @RequestParam String description,
            @RequestParam MultipartFile image
    ) {
        String imagePath = "../src/main/resources/upload/images" + image.getOriginalFilename();
        roomService.addRoom(roomNumber,pricePerNight, maxPeople, available,bedSize,roomType,description, imagePath);
        return "redirect:/admin";
    }
}
