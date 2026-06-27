package com.hoainhi.sportfields.controller;

import com.hoainhi.sportfields.dto.FacilityDTO;
import com.hoainhi.sportfields.entity.Facility;
import com.hoainhi.sportfields.entity.User;
import com.hoainhi.sportfields.service.config.CloudinaryService;
import com.hoainhi.sportfields.service.FacilityService;
import com.hoainhi.sportfields.service.config.WardSevice;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class FacilityController {
    @Autowired
    private FacilityService facilityService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private WardSevice wardSevice;

    @GetMapping("/add")
    public String addFacility(Model model){
        model.addAttribute("facilityDTO" , new FacilityDTO());
        model.addAttribute("wards", wardSevice.getDaNangWards());
        return "owner/facility/Registration";
    }

    @PostMapping("/add")
    public String saveFacility(@ModelAttribute FacilityDTO facilityDTO, @RequestParam("image") MultipartFile image, HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        String imglUrl = cloudinaryService.uploadFile(image);
        facilityDTO.setImg_url(imglUrl);
        facilityService.addfacility(facilityDTO, user);
        return "redirect:/facilities_owner";

    }
    @GetMapping("/facilities_owner")
    public String listFacilities(Model model, HttpSession session){
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }

        List<Facility> facilities = facilityService.getFacilitiesByUser(loginUser);
        model.addAttribute("facilities", facilities);
        return "owner/facility/Facility";
    }
}
