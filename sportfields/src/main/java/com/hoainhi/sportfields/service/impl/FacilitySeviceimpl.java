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

import java.util.List;

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

    @Override
    public List<Facility> getFacilitiesByUser(User user) {
        return faciRepository.findByUser_Id(user.getId());
    }

    @Override
    public List<Facility> getPending() {

        return faciRepository.findByStatus(FaciStatus.PENDING);
    }

    @Override
    public void approve(Long id) {
        Facility facility = faciRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay"));
        facility.setStatus(FaciStatus.APPROVED);
        faciRepository.save(facility);

    }

    @Override
    public Facility findById(Long id) {
        return faciRepository.findById(id).orElseThrow();
    }

    @Override
    public Facility updateFacility(User user, FacilityDTO facilityDTO) {

        Facility facility = faciRepository.findById(facilityDTO.getId_facility())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy"));

        facility.setName_facility(facilityDTO.getName_facility());
        facility.setAddress(facilityDTO.getAddress());
        facility.setPhone(facilityDTO.getPhone());
        facility.setDescription(facilityDTO.getDescription());
        facility.setWards(facilityDTO.getWards());

        facility.setUser(user);

        if (facilityDTO.getImg_url() != null) {
            facility.setImg_url(facilityDTO.getImg_url());
        }

        return faciRepository.save(facility);
    }

    @Override
    public FacilityDTO getFacilityById(Long id) {
        Facility facility = faciRepository.findById(id).orElseThrow(() ->  new RuntimeException("Facility not found"));

        FacilityDTO facilityDTO = new FacilityDTO();
        BeanUtils.copyProperties(facility, facilityDTO);
        return facilityDTO;
    }

    @Override
    public List<Facility> getAll() {
        return faciRepository.findAll();
    }

    @Override
    public List<Facility> getApproved() {
         return faciRepository.findByStatus(FaciStatus.APPROVED);
    }

    @Override
    public List<Facility> getFacilityByOwner(Long userId) {
        return faciRepository.findByUser_IdAndStatus(userId, FaciStatus.APPROVED);
    }

    @Override
    public List<Facility> getFacilityByStatus(Long facility) {
        return faciRepository.findByStatus(FaciStatus.APPROVED);
    }

    @Override
    public void deleteFaci(Long id) {
        if(!faciRepository.existsById(id)){
            throw new RuntimeException("Ko tim thay");
        }
        faciRepository.deleteById(id);
    }


}
