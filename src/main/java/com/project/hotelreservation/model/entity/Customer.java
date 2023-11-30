package com.project.hotelreservation.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "customer")
@NoArgsConstructor
public class Customer extends User {
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "reward_points", nullable = false)
    private int rewardPoints;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    public void deductPoints(int pointsUsed) {
        rewardPoints -= pointsUsed;
    }
}
