package com.hoainhi.sportfields.service;

import com.hoainhi.sportfields.dto.CalendarCourtDTO;
import com.hoainhi.sportfields.dto.ScheduleDTO;
import com.hoainhi.sportfields.entity.Schedule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {
    Schedule addSchedule(ScheduleDTO scheduleDTO);
    List<CalendarCourtDTO> getCalendar(Long ownerId);

}
