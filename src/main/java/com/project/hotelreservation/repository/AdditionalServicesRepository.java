package com.project.hotelreservation.repository;

import com.project.hotelreservation.model.entity.AdditionalServices;
import com.project.hotelreservation.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdditionalServicesRepository extends JpaRepository<AdditionalServices, Integer> {
    List<AdditionalServices> findByServiceIdIn(List<Integer> ids);
}

