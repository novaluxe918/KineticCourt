package com.hoainhi.sportfields.service;

import com.hoainhi.sportfields.dto.ScheduleDTO;
import com.hoainhi.sportfields.entity.Schedule;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleService {
    Schedule addSchedule(ScheduleDTO scheduleDTO);

}
