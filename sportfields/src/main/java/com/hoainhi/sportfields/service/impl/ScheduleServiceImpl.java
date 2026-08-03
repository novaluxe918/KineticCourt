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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ScheduleDTO getScheduleDTOById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Schedule với id = " + id));
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setFacility_id(schedule.getCourt().getFacility().getId());
        scheduleDTO.setId_court(schedule.getCourt().getId());
        scheduleDTO.setDate_start(schedule.getDate_start());
        scheduleDTO.setDate_end(schedule.getDate_end());

        List<ScheduleDetailDTO> details = new ArrayList<>();

        for(ScheduleDetails sd : schedule.getScheduleDetails()){

            ScheduleDetailDTO d = new ScheduleDetailDTO();

            d.setId(sd.getId());
            d.setTime_start(sd.getTime_start());
            d.setTime_end(sd.getTime_end());
            d.setPrice(sd.getPrice());
            d.setStatus(sd.getStatus());
            details.add(d);
        }

        scheduleDTO.setScheduleDetails(details);

        return scheduleDTO;
    }

    @Override
    public Schedule updateSchedule(ScheduleDTO dto) {
        Schedule schedule = scheduleRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        Court court = courtRepository.findById(dto.getId_court())
                .orElseThrow(() -> new RuntimeException("Court not found"));


        schedule.setCourt(court);
        schedule.setDate_start(dto.getDate_start());
        schedule.setDate_end(dto.getDate_end());

        // Danh sách detail hiện có trong DB
        List<ScheduleDetails> oldDetails = schedule.getScheduleDetails();


        Map<Long, ScheduleDetails> oldMap = oldDetails.stream()
                .collect(Collectors.toMap(ScheduleDetails::getId, d -> d));

        List<ScheduleDetails> newDetails = new ArrayList<>();

        for (ScheduleDetailDTO item : dto.getScheduleDetails()) {

            ScheduleDetails detail;

            if (item.getId() != null && oldMap.containsKey(item.getId())) {
                // Update
                detail = oldMap.get(item.getId());
            } else {
                // Insert
                detail = new ScheduleDetails();
                detail.setSchedule(schedule);
            }

            detail.setTime_start(item.getTime_start());
            detail.setTime_end(item.getTime_end());
            detail.setPrice(item.getPrice());
            if(item.getStatus() != null){
                detail.setStatus(item.getStatus());
            }

            newDetails.add(detail);
        }

        // Thay danh sách cũ bằng danh sách mới
        oldDetails.clear();
        oldDetails.addAll(newDetails);

       return scheduleRepository.save(schedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Không tìm thấy Schedule"));

        scheduleRepository.delete(schedule);
    }

    @Override
    public Page<Schedule> getScheduleByOwner(Long userId, Pageable pageable) {
        return scheduleRepository.findByCourt_Facility_User_Id(userId, pageable);
    }


}
