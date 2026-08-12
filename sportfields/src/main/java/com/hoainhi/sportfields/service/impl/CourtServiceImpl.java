package com.hoainhi.sportfields.service.impl;

import com.hoainhi.sportfields.dto.CourtDTO;
import com.hoainhi.sportfields.entity.Court;
import com.hoainhi.sportfields.entity.Facility;
import com.hoainhi.sportfields.enums.FaciStatus;
import com.hoainhi.sportfields.repository.CourtRepository;
import com.hoainhi.sportfields.repository.FaciRepository;
import com.hoainhi.sportfields.service.CourtService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        facility.setId(courtDTO.getFacility_id());
        court.setFacility(facility);
        return courtRepository.save(court);
    }

    @Override
    public List<Court> getCourtByFacility(Long facilityId) {
        return courtRepository.findByFacility_Id(facilityId);
    }

    @Override
    public List<Court> getCourtByOwner(Long ownerId) {
        return courtRepository.findByFacility_User_IdAndFacility_Status(
                ownerId,
                FaciStatus.APPROVED
        );
    }

    @Override
    public void delteteCourt(Long id) {
        if(!courtRepository.existsById(id)){
            throw new RuntimeException("ko tim thay");
        }

        courtRepository.deleteById(id);

    }

    @Override
    public Court updateCourt(CourtDTO courtDTO) {
        Court court = courtRepository.findById(courtDTO.getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy sân"));
        court.setName_court(court.getName_court());
        court.setStatus(courtDTO.getStatus());
        Facility facility = faciRepository.findById(courtDTO.getFacility_id()).orElseThrow(() -> new RuntimeException("Không tìm thấy cơ sở"));

        court.setFacility(facility);

        return null;
    }


    @Override
    public List<Court> getAllCourt() {
        return courtRepository.findAll();
    }

    @Override
    public Court getById(Long id) {
        return courtRepository.findById(id).orElseThrow(null);
    }

    @Override
    public void save(Court court) {
        courtRepository.save(court);
    }

    @Override
    public Page<Court> getCourtByOwnerPaging(Long ownerId, Pageable pageable) {
        return courtRepository.findPageByFacility_User_IdAndFacility_Status(
                ownerId,
                FaciStatus.APPROVED,
                pageable
        );
    }

    @Override
    public Page<Court> getCourtByFacilityPaging(Long facilityId, Pageable pageable) {
        return courtRepository.findPageByFacility_IdAndFacility_Status(facilityId,FaciStatus.APPROVED, pageable);
    }


}
