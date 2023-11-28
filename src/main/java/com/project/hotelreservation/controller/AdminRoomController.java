package com.project.hotelreservation.controller;

import com.project.hotelreservation.enums.BedSize;
import com.project.hotelreservation.enums.RoomType;
import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

/**
 * @author Luxin
 */
@Controller
@AllArgsConstructor
public class AdminRoomController {
    private RoomService roomService;

    // display the list of rooms
    @GetMapping("/admin")
    public String viewRoomPage(Model model) {
        model.addAttribute("listRooms", roomService.getAllRooms());
        return "admin/admin";
    }

    // show the room form for creating new room
    @GetMapping("/new-room")
    public String showAddRoomForm() {
        return "admin/new-room";
    }

    // create the new room and store it in the database
    @PostMapping("/new-room")
    public String addRoom(@RequestParam Integer roomNumber, @RequestParam BigDecimal pricePerNight, @RequestParam Integer maxPeople, @RequestParam boolean available, @RequestParam BedSize bedSize, @RequestParam RoomType roomType, @RequestParam String description, @RequestParam MultipartFile image) {

        String imagePath = "";

        try {

            String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));

            // Obtain the absolute path to the "images" folder inside the project
            String folderPath = new File("src/main/resources/static/images/").getAbsolutePath();

            // Create a Path object for the destination file
            Path path = Paths.get(folderPath + File.separator + fileName);

            // Copy the input stream of the image to the destination file
            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            }

            //save the file path to the database
            imagePath = image.getOriginalFilename();


        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception to handle error
        }

        //save all the data in room database
        roomService.addRoom(roomNumber, pricePerNight, maxPeople, available, bedSize, roomType, description, imagePath);

        return "redirect:/admin";
    }

    // show room form to update the detail of the room
    @GetMapping("/showRoomFormForUpdate/{id}")
    public String showRoomFormForUpdate(@PathVariable(value = "id") Integer roomId, Model model) {

        // get room from the service
        Room room = roomService.getRoomById(roomId);

        // set the room as a model attribute to pre-populate the form
        model.addAttribute("room", room);
        return "admin/update-room";

    }

    @PostMapping("/update-room")
    public String saveRoom(@ModelAttribute("room") Room room, @RequestParam(name = "image", required = false) MultipartFile image) {

        try {
            if (image != null && !image.isEmpty()) {
                // A new file is chosen, handle it
                String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));

                // Obtain the absolute path to the "images" folder inside the project
                String folderPath = new File("src/main/resources/static/images/").getAbsolutePath();

                // Create a Path object for the destination file
                Path path = Paths.get(folderPath + File.separator + fileName);

                // Copy the input stream of the image to the destination file
                try (InputStream inputStream = image.getInputStream()) {
                    Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
                }

                // Save the file path to the database
                String imagePath = image.getOriginalFilename();
                room.setImagePath(imagePath);
            } else {
                // No new file is chosen, keep the current file path
                Room existingRoom = roomService.getRoomById(room.getRoomId());
                room.setImagePath(existingRoom.getImagePath());
            }

            // Save updated room to the database
            roomService.saveRoom(room);

        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception to handle error
        }

        return "redirect:/admin";
    }

    @GetMapping("/delete-room/{id}")
    public String deleteRoom(@PathVariable(value = "id") Integer roomId) {
        // call delete room method
        this.roomService.deleteRoomById(roomId);
        return "redirect:/admin";
    }

    @GetMapping("/rooms/search")
    public String searchARoom(@RequestParam Integer roomId, Model model) {
        List<Room> foundRooms = roomService.searchRoomsByRoomId(roomId);
        model.addAttribute("listRooms", foundRooms);
        return "admin/list-rooms";
    }
}