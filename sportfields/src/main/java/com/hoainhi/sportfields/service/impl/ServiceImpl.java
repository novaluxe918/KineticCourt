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
    public Page<Services> getServiceByFacility(Long facilityId, Pageable pageable) {
        return serviceRepository.findByFacility_Id(facilityId, pageable);
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

    @Override
    public Services updateService(ServiceDTO dto) {
        Services services = serviceRepository.findById(dto.getId()).orElseThrow();
        services.setTitle(dto.getTitle());
        services.setDescription(dto.getDescription());
        services.setPrice(dto.getPrice());
        services.setUnit(dto.getUnit());
        Facility facility = faciRepository.findById(dto.getFacilityId()).orElseThrow();
        services.setFacility(facility);
        return serviceRepository.save(services);
    }

    @Override
    public ServiceDTO getServiceDTOById(Long id) {
        Services services = serviceRepository.findById(id).orElseThrow();
        ServiceDTO dto = new ServiceDTO();
        BeanUtils.copyProperties(services, dto);
        dto.setFacilityId(services.getFacility().getId());
        return dto;
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }


}
