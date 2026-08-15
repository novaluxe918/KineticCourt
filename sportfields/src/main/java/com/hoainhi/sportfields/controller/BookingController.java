package com.hoainhi.sportfields.controller;

import com.hoainhi.sportfields.dto.FacilityDTO;
import com.hoainhi.sportfields.dto.ShowDTO;
import com.hoainhi.sportfields.dto.TimeShowDTO;
import com.hoainhi.sportfields.entity.Court;
import com.hoainhi.sportfields.entity.Facility;
import com.hoainhi.sportfields.entity.ScheduleDetails;
import com.hoainhi.sportfields.enums.ScheduleStatus;
import com.hoainhi.sportfields.repository.FaciRepository;
import com.hoainhi.sportfields.service.impl.CourtServiceImpl;
import com.hoainhi.sportfields.service.impl.FacilitySeviceimpl;
import com.hoainhi.sportfields.service.impl.ScheduleDetailSerivceimp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("booking")
public class BookingController {

    @Autowired
    private CourtServiceImpl courtService;

    @Autowired
    private ScheduleDetailSerivceimp scheduleDetailSerivceimp;

    @GetMapping("/book")
    public String bookingClient(@RequestParam Long facilityId, Model model,
                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date){

        if(date == null){
            date = LocalDate.now();
        }  // thoi gian hien tai
        List<String> timeSlots = new ArrayList<>(); // dto time , status
        LocalTime time = LocalTime.of(5, 0);
        LocalTime end = LocalTime.of(22, 0);
        while (!time.isAfter(end)){
            timeSlots.add(time.toString());
            time = time.plusMinutes(30);
        }
        List<Court> courts = courtService.getCourtByFacility(facilityId);
        // courts => list schedudetail => status trong => dong
        List<ShowDTO> showDTOS = new ArrayList<>();
        for(Court court : courts){
            List<TimeShowDTO> timeShowDTOS = new ArrayList<>();
            for(String timeValue : timeSlots){
                List<ScheduleDetails> scheduleDetails = scheduleDetailSerivceimp.getScheduleDetails(court.getId(), date);
                LocalTime currentTime  = LocalTime.parse(timeValue);
              TimeShowDTO timeShowDTO = new TimeShowDTO();
              timeShowDTO.setTime(timeValue);
                boolean closed = scheduleDetails.stream()
                        .anyMatch(detail -> {

                            LocalTime startTime =
                                    detail.getTime_start();

                            LocalTime endTime =
                                    detail.getTime_end();

                            return !currentTime.isBefore(startTime)
                                    && currentTime.isBefore(endTime);
                        });
                if (closed) {

                    timeShowDTO.setStatus(
                            ScheduleStatus.CLOSED
                    );

                } else {

                    timeShowDTO.setStatus(
                            ScheduleStatus.AVAILABLE
                    );
                }


                timeShowDTOS.add(timeShowDTO);
            }

           ShowDTO showDTO = new ShowDTO();
            showDTO.setCourt(court);
            showDTO.setTimeShowDTOS(timeShowDTOS);
            showDTOS.add(showDTO);
        }
        model.addAttribute("date", date);
        model.addAttribute("facilityId", facilityId);
        model.addAttribute("showDTOS", showDTOS);
        model.addAttribute("timeSlot", timeSlots);

        return "client/booking/Booking";
    }

}
