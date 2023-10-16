package com.project.hotelreservation.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date checkInDate;
    private Date checkOutDate;

    @ManyToOne
    private Room room;

    @ManyToMany
    private List<Services> services;
}