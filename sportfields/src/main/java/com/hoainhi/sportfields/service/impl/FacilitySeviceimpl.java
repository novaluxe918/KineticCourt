package com.hoainhi.sportfields.service.impl;

import com.hoainhi.sportfields.dto.FacilityDTO;
import com.hoainhi.sportfields.entity.Facility;
import com.hoainhi.sportfields.repository.FaciRepository;
import com.hoainhi.sportfields.service.FacilityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacilitySeviceimpl  implements FacilityService {

    @Autowired
    private FaciRepository faciRepository;

    @Override
    public Facility addfacility(FacilityDTO facilityDTO) {
        Facility facility = new Facility();
        BeanUtils.copyProperties(facilityDTO, facility);
        return faciRepository.save(facility);
    }
}
