package com.hoainhi.sportfields.service.impl;

import com.hoainhi.sportfields.dto.ServiceDTO;
import com.hoainhi.sportfields.entity.Facility;
import com.hoainhi.sportfields.entity.Services;
import com.hoainhi.sportfields.repository.FaciRepository;
import com.hoainhi.sportfields.repository.ServiceRepository;
import com.hoainhi.sportfields.service.Service_sv;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceImpl implements Service_sv {
     @Autowired
     private FaciRepository faciRepository;

     @Autowired
     private ServiceRepository serviceRepository;

    @Override
    public Page<Services> findPaginated(Pageable pageable) {
        return serviceRepository.findAll(pageable);
    }

    @Override
    public List<Services> getServiceByFacility(Long facilityId) {
        return serviceRepository.findByFacility_Id(facilityId);
    }

    @Override
    public Services addService(ServiceDTO serviceDTO) {
        Services services = new Services();
        BeanUtils.copyProperties(serviceDTO, services);
        Facility facility = faciRepository.findById(serviceDTO.getFacilityId()).orElse(null);
        services.setFacility(facility);
        return serviceRepository.save(services) ;
    }

    @Override
    public Page<Services> findByTitleContaining(String name, Pageable pageable) {
        return serviceRepository.findByTitleContaining(name, pageable);
    }


}
