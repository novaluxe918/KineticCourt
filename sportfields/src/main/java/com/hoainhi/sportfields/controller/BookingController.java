package com.hoainhi.sportfields.controller;

import com.hoainhi.sportfields.dto.FacilityDTO;
import com.hoainhi.sportfields.entity.Court;
import com.hoainhi.sportfields.entity.Facility;
import com.hoainhi.sportfields.repository.FaciRepository;
import com.hoainhi.sportfields.service.impl.CourtServiceImpl;
import com.hoainhi.sportfields.service.impl.FacilitySeviceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("booking")
public class BookingController {

    @Autowired
    private CourtServiceImpl courtService;

    @GetMapping("/book")
    public String bookingClient(@RequestParam Long facilityId, Model model){
  // thoi gian hien tai
        List<String> timeSlots = new ArrayList<>();
        LocalTime time = LocalTime.of(5, 0);
        LocalTime end = LocalTime.of(22, 0);
        while (!time.isAfter(end)){
            timeSlots.add(time.toString());
            time = time.plusMinutes(30);
        }
        List< Court> courts = courtService.getCourtByFacility(facilityId);
        model.addAttribute("facilityId", facilityId);
        model.addAttribute("courts", courts);
        model.addAttribute("timeSlot", timeSlots);

        return "client/booking/Booking";
    }
}
