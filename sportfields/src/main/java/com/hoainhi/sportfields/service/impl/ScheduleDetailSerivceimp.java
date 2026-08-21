package com.hoainhi.sportfields.service.impl;

import com.hoainhi.sportfields.entity.ScheduleDetails;
import com.hoainhi.sportfields.repository.ScheduleDetailRepository;
import com.hoainhi.sportfields.service.ScheduleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleDetailSerivceimp implements ScheduleDetailService {
    @Autowired
    private ScheduleDetailRepository scheduleDetailRepository;

    @Override
    public List<ScheduleDetails> getByFacility(Long facilityId) {
        return scheduleDetailRepository.findBySchedule_Court_Facility_Id(facilityId);
    }

    @Override
    public List<ScheduleDetails> getAll() {
        return  scheduleDetailRepository.findAll();
    }

    @Override
    public List<ScheduleDetails> getScheduleDetails(Long courtId, LocalDate date) {
        return scheduleDetailRepository.findByTimeScheduleDetails(courtId, date);
    }

    @Override
    public List<ScheduleDetails> getByOwner(Long ownerId) {
        return scheduleDetailRepository.findByOwner(ownerId);
    }

    @Override
    public List<ScheduleDetails> getByIds(List<Long> ids) {
        return scheduleDetailRepository.findByIdIn(ids);
    }
}
