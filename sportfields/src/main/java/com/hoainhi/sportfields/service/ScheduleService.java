package com.hoainhi.sportfields.service;

import com.hoainhi.sportfields.dto.CalendarCourtDTO;
import com.hoainhi.sportfields.dto.ScheduleDTO;
import com.hoainhi.sportfields.entity.Schedule;
import com.hoainhi.sportfields.entity.ScheduleDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {
    Schedule addSchedule(ScheduleDTO scheduleDTO);
    ScheduleDTO getScheduleDTOById(Long id);
    Schedule updateSchedule(ScheduleDTO dto);
    void deleteSchedule(Long id);

}
