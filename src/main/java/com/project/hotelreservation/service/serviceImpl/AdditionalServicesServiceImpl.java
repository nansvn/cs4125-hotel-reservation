package com.project.hotelreservation.service.serviceImpl;
import com.project.hotelreservation.model.entity.AdditionalServices;
import com.project.hotelreservation.repository.AdditionalServicesRepository;
import com.project.hotelreservation.service.AdditionalServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdditionalServicesServiceImpl implements AdditionalServicesService {
    @Autowired
    private AdditionalServicesRepository additionalServicesRepository;

    @Override
    public List<AdditionalServices> getAllAdditionalServices() {
        return additionalServicesRepository.findAll();
    }
}
