package com.hoainhi.sportfields.controller;

import com.hoainhi.sportfields.dto.CalendarCourtDTO;
import com.hoainhi.sportfields.dto.CourtDTO;
import com.hoainhi.sportfields.dto.ScheduleDTO;
import com.hoainhi.sportfields.entity.Court;
import com.hoainhi.sportfields.entity.Facility;
import com.hoainhi.sportfields.entity.ScheduleDetails;
import com.hoainhi.sportfields.entity.User;
import com.hoainhi.sportfields.service.impl.CourtServiceImpl;
import com.hoainhi.sportfields.service.impl.FacilitySeviceimpl;
import com.hoainhi.sportfields.service.impl.ScheduleDetailSerivceimp;
import com.hoainhi.sportfields.service.impl.ScheduleServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

    @Autowired
    private ScheduleDetailSerivceimp scheduleDetailSerivceimp;

    @GetMapping("/schedule_owner")
    public String showSchedule(
            @RequestParam(required = false) Long facilityId,
            Model model,
            HttpServletRequest request,
            HttpSession session){

        User user = (User) session.getAttribute("loginUser");

        List<ScheduleDetails > schedules;

        if (facilityId == null){
            schedules = scheduleDetailSerivceimp.getAll();
        }else {
            schedules = scheduleDetailSerivceimp.getByFacility(facilityId);
        }
        List<Facility> facilities =
                facilitySeviceimpl.getFacilityByOwner(user.getId());

        model.addAttribute("selectFacility", facilityId);
        model.addAttribute("facilities", facilities);
        model.addAttribute("currentUrl", request.getRequestURI());
        model.addAttribute("schedules", schedules);

        return "owner/schedule/Schedule";
    }

    @GetMapping("/edit/{id}")
    public String editSchedule(@PathVariable Long id, Model model, HttpSession session){

        User user = (User) session.getAttribute("loginUser");
        ScheduleDTO scheduleDTO = scheduleService.getScheduleDTOById(id);
        scheduleDTO.setIsEdit(true);
        model.addAttribute("schedules", scheduleDTO);
        model.addAttribute("facilities", facilitySeviceimpl.getFacilityByOwner(user.getId()));
        model.addAttribute("courts", courtService.getCourtByOwner(user.getId()));

        return "owner/schedule/AddSchedule";

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
    public String saveSchedule(@Validated @ModelAttribute("schedules") ScheduleDTO scheduleDTO, BindingResult result, Model model){
        if(result.hasErrors()){

            return "owner/schedule/AddSchedule";

        }
        if (scheduleDTO.getId() == null){
            scheduleService.addSchedule(scheduleDTO);
        }else {
            scheduleService.updateSchedule(scheduleDTO);
        }

        return "redirect:/schedule/add";
    }


}
