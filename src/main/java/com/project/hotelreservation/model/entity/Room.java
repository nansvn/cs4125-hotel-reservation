package com.project.hotelreservation.model.entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "room_number")
    private String roomNumber;

    @Column(name = "max_people")
    private Integer maxPeople;

    @Column(name = "available")
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "room_detail")
    private RoomDetail roomDetail;
}
