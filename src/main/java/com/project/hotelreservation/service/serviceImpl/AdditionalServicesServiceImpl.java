package com.project.hotelreservation.service.serviceImpl;
import com.project.hotelreservation.model.entity.AdditionalServices;
import com.project.hotelreservation.repository.AdditionalServicesRepository;
import com.project.hotelreservation.service.AdditionalServicesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdditionalServicesServiceImpl implements AdditionalServicesService {
    private AdditionalServicesRepository additionalServicesRepository;

    @Override
    public List<AdditionalServices> getAllAdditionalServices() {
        return additionalServicesRepository.findAll();
    }

    @Override
    public List<AdditionalServices> getServicesByIds(List<Integer> ids) {
        return additionalServicesRepository.findByServiceIdIn(ids);
    }
}
