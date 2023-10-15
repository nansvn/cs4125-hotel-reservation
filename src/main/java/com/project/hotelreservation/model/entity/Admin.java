package com.project.hotelreservation.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "admin")
public class Admin extends User {
    @Column(name = "admin_name")
    private String adminName;
}
