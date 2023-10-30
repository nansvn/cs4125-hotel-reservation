package com.project.hotelreservation.repository;

import com.project.hotelreservation.model.entity.AdditionalServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalServicesRepository extends JpaRepository<AdditionalServices, Integer> {
}

