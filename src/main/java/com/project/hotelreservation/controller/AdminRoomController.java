package com.project.hotelreservation.controller;

import com.project.hotelreservation.enums.BedSize;
import com.project.hotelreservation.enums.RoomType;
import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@AllArgsConstructor
@Controller
public class AdminRoomController {
    //Display list of rooms
    private RoomService roomService;
    @GetMapping("/admin")
    public String viewRoomPage(Model model){
        model.addAttribute("listRooms",roomService.getAllRooms());
    return "admin";
    }

    //show the room form for creating new room
    @GetMapping("/new_room")
    public String showAddRoomForm() {
        return "new_room";
    }

    //create the room and store in the database
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
        //create the room and save room to database here
        roomService.addRoom(roomNumber,pricePerNight, maxPeople, available,bedSize,roomType,description, imagePath);
        return "redirect:/admin";
    }

    //show room form to update the detail of the room
    @GetMapping("/showRoomFormForUpdate/{id}")
    public String showRoomFormForUpdate(@PathVariable(value = "id") Integer roomId, Model model){

        //get room from the service
        Room room = roomService.showRoomById(roomId);

        //set the room as a model attribute to pre-populate the form
        model.addAttribute("room",room);
        return "update_room";

    }


    @PostMapping("/update_room")
    public String saveRoom(@ModelAttribute("room") Room room) {
        //save employee to database
        roomService.saveRoom(room);
        return "redirect:/admin";

    }





}
