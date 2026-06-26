package com.hoainhi.sportfields.service.impl;

import com.hoainhi.sportfields.dto.FacilityDTO;
import com.hoainhi.sportfields.entity.Facility;
import com.hoainhi.sportfields.entity.User;
import com.hoainhi.sportfields.enums.FaciStatus;
import com.hoainhi.sportfields.repository.FaciRepository;
import com.hoainhi.sportfields.service.FacilityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.stereotype.Service;

@Service
public class FacilitySeviceimpl  implements FacilityService {

    @Autowired
    private FaciRepository faciRepository;

    @Override
    public Facility addfacility(FacilityDTO facilityDTO, User user) {
        Facility facility = new Facility();
        BeanUtils.copyProperties(facilityDTO, facility);
        facility.setStatus(FaciStatus.PENDING);
        facility.setUser(user);
        return faciRepository.save(facility);
    }
}
