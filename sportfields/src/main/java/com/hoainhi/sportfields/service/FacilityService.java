package com.hoainhi.sportfields.service;

import com.hoainhi.sportfields.dto.FacilityDTO;
import com.hoainhi.sportfields.entity.Facility;
import com.hoainhi.sportfields.entity.User;

import java.util.List;

public interface FacilityService {
    public Facility addfacility(FacilityDTO facilityDTO,  User user);
    List<Facility> getFacilitiesByUser(User user);

}
