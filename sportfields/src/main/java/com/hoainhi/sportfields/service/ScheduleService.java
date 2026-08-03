package com.hoainhi.sportfields.service;
import com.hoainhi.sportfields.dto.ScheduleDTO;
import com.hoainhi.sportfields.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {
    Schedule addSchedule(ScheduleDTO scheduleDTO);
    ScheduleDTO getScheduleDTOById(Long id);
    Schedule updateSchedule(ScheduleDTO dto);
    void deleteSchedule(Long id);
    Page<Schedule> getScheduleByOwner(
            Long userId,
            Pageable pageable);

}
