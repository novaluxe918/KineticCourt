package com.hoainhi.sportfields.controller;

import com.hoainhi.sportfields.dto.CourtDTO;
import com.hoainhi.sportfields.entity.Court;
import com.hoainhi.sportfields.entity.Facility;
import com.hoainhi.sportfields.enums.Status;
import com.hoainhi.sportfields.service.impl.CourtServiceImpl;
import com.hoainhi.sportfields.service.impl.FacilitySeviceimpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("court")
public class CourtController {
    @Autowired
    private FacilitySeviceimpl facilitySeviceimpl;

    @Autowired
    private CourtServiceImpl courtService;
    @GetMapping("/court_owner")
    public String showCourt(HttpServletRequest request, Model model){
        model.addAttribute("currentUrl", request.getRequestURI());
        return "owner/courts/Court";
    }

    @GetMapping("/add")
    public String addCourt(Model model){
        model.addAttribute("court", new CourtDTO());
        List<Facility> facilities = facilitySeviceimpl.getAll();
         model.addAttribute("facilities", facilities);

        return "owner/courts/AddCourt";
    }

    @PostMapping("/save")
    public String saveCourt(@ModelAttribute("court") CourtDTO courtDTO){
        courtService.addCourt(courtDTO);
        return "owner/courts/AddCourt";

    }
}
