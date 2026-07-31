package com.hoainhi.sportfields.service;

import com.hoainhi.sportfields.entity.ScheduleDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleDetailService {
    List<ScheduleDetails> getByFacility(Long facilityId);

    List<ScheduleDetails> getAll();
}
