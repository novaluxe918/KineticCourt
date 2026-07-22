package com.hoainhi.sportfields.service.impl;

import com.hoainhi.sportfields.dto.CalendarCourtDTO;
import com.hoainhi.sportfields.dto.ScheduleDTO;
import com.hoainhi.sportfields.dto.ScheduleDetailDTO;
import com.hoainhi.sportfields.entity.Court;
import com.hoainhi.sportfields.entity.Facility;
import com.hoainhi.sportfields.entity.Schedule;
import com.hoainhi.sportfields.entity.ScheduleDetails;
import com.hoainhi.sportfields.enums.ScheduleStatus;
import com.hoainhi.sportfields.repository.CourtRepository;
import com.hoainhi.sportfields.repository.FaciRepository;
import com.hoainhi.sportfields.repository.ScheduleDetailRepository;
import com.hoainhi.sportfields.repository.ScheduleRepository;
import com.hoainhi.sportfields.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ScheduleDetailRepository scheduleDetailRepository;

    @Autowired
    private CourtRepository courtRepository;

    @Override
    public Schedule addSchedule(ScheduleDTO scheduleDTO) {
        Court court = courtRepository.findById(scheduleDTO.getId_court()).orElseThrow(() -> new RuntimeException("Không tìm thấy sân"));
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        schedule.setCourt(court);
        scheduleRepository.save(schedule);
        for(ScheduleDetailDTO dto : scheduleDTO.getScheduleDetails()){
            ScheduleDetails scheduleDetails = new ScheduleDetails();
            BeanUtils.copyProperties(dto, scheduleDetails);
            scheduleDetails.setStatus(ScheduleStatus.AVAILABLE);
            scheduleDetails.setSchedule(schedule);
            scheduleDetailRepository.save(scheduleDetails);
        }
        return schedule;
    }

    @Override
    public List<CalendarCourtDTO> getCalendar(Long ownerId) {
        List<Court> courts = courtRepository.findByFacility_User_Id(ownerId);
        List<CalendarCourtDTO> calendarCourtDTOS = new ArrayList<>();
         for(Court court : courts){
             CalendarCourtDTO courtDTO = new CalendarCourtDTO();
             courtDTO.setCourtId(court.getId());
             courtDTO.setCourtName(court.getName_court());
             List<ScheduleDetailDTO> scheduleDetailDTOS = new ArrayList<>();
             for(Schedule schedule : court.getSchedules()){
                 for(ScheduleDetails detail : schedule.getScheduleDetails()){
                     ScheduleDetailDTO dto = new ScheduleDetailDTO();
                     dto.setTime_start(detail.getTime_start());
                     dto.setTime_end(detail.getTime_end());
                     dto.setPrice(detail.getPrice());
                     dto.setStatus(detail.getStatus());
                     scheduleDetailDTOS.add(dto);


                 }

             }
             courtDTO.setSlots(scheduleDetailDTOS);
             calendarCourtDTOS.add(courtDTO);

         }
        return calendarCourtDTOS;
    }
}
