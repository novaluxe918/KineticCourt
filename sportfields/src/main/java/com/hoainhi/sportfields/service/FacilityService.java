package com.hoainhi.sportfields.service;

import com.hoainhi.sportfields.dto.FacilityDTO;
import com.hoainhi.sportfields.entity.Facility;
import com.hoainhi.sportfields.entity.User;

import java.util.List;

public interface FacilityService {
    public Facility addfacility(FacilityDTO facilityDTO,  User user);
    List<Facility> getFacilitiesByUser(User user);
    List<Facility> getPending();

    public void approve(Long id);

    Facility findById(Long id);

    Facility updateFacility( User user, FacilityDTO facilityDTO);
    FacilityDTO getFacilityById(Long id);
    List<Facility> getAll();
    List<Facility> getApproved();
}
