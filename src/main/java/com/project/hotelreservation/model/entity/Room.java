package com.project.hotelreservation.model.entity;

import com.project.hotelreservation.enums.BedSize;
import com.project.hotelreservation.enums.RoomType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "room_number")
    private String roomNumber;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "max_people")
    private Integer maxPeople;

    @Column(name = "available")
    private boolean available;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
    private RoomType roomType;

    @Enumerated(EnumType.STRING)
    @Column(name = "bed_size")
    private BedSize bedSize;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    private Booking booking;
}
