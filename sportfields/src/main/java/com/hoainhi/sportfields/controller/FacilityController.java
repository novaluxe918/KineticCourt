package com.hoainhi.sportfields.controller;

import com.hoainhi.sportfields.dto.FacilityDTO;
import com.hoainhi.sportfields.service.FacilityService;
import com.hoainhi.sportfields.service.WardSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FacilityController {
    @Autowired
    private FacilityService facilityService;

    @Autowired
    private WardSevice wardSevice;

    @GetMapping("/add")
    public String addFacility(Model model){
        model.addAttribute("facilityDTO" , new FacilityDTO());
        model.addAttribute("wards", wardSevice.getDaNangWards());
        return "owner/facility/Registration";
    }

    @PostMapping("/add")
    public String saveFacility(@ModelAttribute FacilityDTO facilityDTO){

        facilityService.addfacility(facilityDTO);
        return "owner/facility/Facility";

    }
}
