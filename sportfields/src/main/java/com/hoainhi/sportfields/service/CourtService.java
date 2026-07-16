package com.hoainhi.sportfields.service;

import com.hoainhi.sportfields.dto.CourtDTO;
import com.hoainhi.sportfields.entity.Court;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CourtService {
    Court addCourt(CourtDTO courtDTO);
    List<Court> getCourtByFacility(Long facilityId);
    List<Court> getCourtByOwner(Long ownerId);

    public void delteteCourt(Long id);

    List<Court> getAllCourt();
}
