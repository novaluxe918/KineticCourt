package com.hoainhi.sportfields.controller;

import com.hoainhi.sportfields.dto.CalendarCourtDTO;
import com.hoainhi.sportfields.dto.CourtDTO;
import com.hoainhi.sportfields.dto.ScheduleDTO;
import com.hoainhi.sportfields.entity.Court;
import com.hoainhi.sportfields.entity.Facility;
import com.hoainhi.sportfields.entity.User;
import com.hoainhi.sportfields.service.impl.CourtServiceImpl;
import com.hoainhi.sportfields.service.impl.FacilitySeviceimpl;
import com.hoainhi.sportfields.service.impl.ScheduleServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("schedule")
public class ScheduleController {
    @Autowired
    private FacilitySeviceimpl facilitySeviceimpl;

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @Autowired
    private CourtServiceImpl courtService;

    @GetMapping("/schedule_owner")
    public String showSchedule(
            Model model,
            HttpServletRequest request,
            HttpSession session){

        User user = (User) session.getAttribute("loginUser");

        List<Court> courts = courtService.getCourtByOwner(user.getId());
        List<CalendarCourtDTO> calendarCourtDTOS = scheduleService.getCalendar(user.getId());
        List<Facility> facilities =
                facilitySeviceimpl.getFacilityByOwner(user.getId());
        List<String> times = new ArrayList<>();

        for(int i = 5; i <= 24; i++){

            if(i == 24){
                times.add("12:00 AM");
            }
            else if(i < 12){
                times.add(String.format("%02d:00 AM", i));
            }
            else if(i == 12){
                times.add("12:00 PM");
            }
            else{
                times.add(String.format("%02d:00 PM", i - 12));
            }

        }

        model.addAttribute("time", times);
        model.addAttribute("calendar", calendarCourtDTOS);
        model.addAttribute("facilities", facilities);
        model.addAttribute("currentUrl", request.getRequestURI());

        return "owner/schedule/Schedule";
    }

    @GetMapping("/calendar/{facilityId}")
    @ResponseBody
    public List<CalendarCourtDTO> getCalendar(@PathVariable Long facilityId){

        return scheduleService.getCalendarByFacility(facilityId);

    }

    @GetMapping("/add")
    public String addSchedule(Model model, HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        List<Facility> facilities = facilitySeviceimpl.getFacilityByOwner(user.getId());
        List<Court> courts = courtService.getAllCourt();
        model.addAttribute("courts", courts);
        model.addAttribute("schedules", new ScheduleDTO());
        model.addAttribute("facilities", facilities);

        return "owner/schedule/AddSchedule";
    }

    @PostMapping("/save")
    public String saveSchedule(@ModelAttribute("schedules") ScheduleDTO scheduleDTO){
        scheduleService.addSchedule(scheduleDTO);
        return "redirect:/schedule/add";
    }


}
