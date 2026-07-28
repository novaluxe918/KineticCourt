package com.hoainhi.sportfields.service;

import com.hoainhi.sportfields.dto.ServiceDTO;
import com.hoainhi.sportfields.entity.Services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface Service_sv {
    Page<Services> findPaginated(Pageable pageable);
    Page<Services> getServiceByFacility(Long facilityId, Pageable pageable);
    Services addService(ServiceDTO serviceDTO);
    Page<Services> findByTitleContaining(String name, Pageable pageable);
}
