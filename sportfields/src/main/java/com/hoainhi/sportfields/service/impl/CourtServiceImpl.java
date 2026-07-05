package com.hoainhi.sportfields.service.impl;

import com.hoainhi.sportfields.dto.CourtDTO;
import com.hoainhi.sportfields.entity.Court;
import com.hoainhi.sportfields.entity.Facility;
import com.hoainhi.sportfields.repository.CourtRepository;
import com.hoainhi.sportfields.repository.FaciRepository;
import com.hoainhi.sportfields.service.CourtService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourtServiceImpl implements CourtService {
    @Autowired
    private FaciRepository faciRepository;

    @Autowired
    private CourtRepository courtRepository;

    @Override
    public Court addCourt(CourtDTO courtDTO) {
        Court court = new Court();
        BeanUtils.copyProperties(courtDTO, court);
        Facility facility = new Facility();
        facility.setId_facility(courtDTO.getFacility_id());
        court.setFacility(facility);
        return courtRepository.save(court);
    }
}
