package com.hoainhi.sportfields.service;

import com.hoainhi.sportfields.dto.FacilityDTO;
import com.hoainhi.sportfields.entity.Facility;
import com.hoainhi.sportfields.entity.User;

public interface FacilityService {
    public Facility addfacility(FacilityDTO facilityDTO,  User user);

}
