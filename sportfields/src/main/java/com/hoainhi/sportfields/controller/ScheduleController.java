package com.hoainhi.sportfields.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("schedule")
public class ScheduleController {

    @GetMapping("/schedule_owner")
    public String showSchedule(Model model, HttpServletRequest request){
        model.addAttribute("currentUrl", request.getRequestURI());
        return "owner/schedule/Schedule";
    }
}
