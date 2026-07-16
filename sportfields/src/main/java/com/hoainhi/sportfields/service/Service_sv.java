package com.hoainhi.sportfields.service;

import com.hoainhi.sportfields.dto.ServiceDTO;
import com.hoainhi.sportfields.entity.Services;
import org.springframework.stereotype.Service;

@Service
public interface Service_sv {
    Services addService(ServiceDTO serviceDTO);
}
