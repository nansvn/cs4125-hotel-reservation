package com.project.hotelreservation.model.entity;

import com.project.hotelreservation.enums.BedSize;
import com.project.hotelreservation.enums.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "room_number")
    private Integer roomNumber;

    @Column(nullable = false, name = "price_per_night")
    private BigDecimal pricePerNight;

    @Column(name = "max_people")
    private Integer maxPeople;

    @Column(name = "available")
    private boolean available;

    @Enumerated(EnumType.STRING)
    @Column(name = "bed_size")
    private BedSize bedSize;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
    private RoomType roomType;

    @Column(name = "description")
    private String description;

    @Column(name = "image_path")
    private String imagePath;

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    private Booking booking;
}
