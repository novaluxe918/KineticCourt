package com.hoainhi.sportfields.controller;

import com.hoainhi.sportfields.dto.FacilityDTO;
import com.hoainhi.sportfields.entity.Facility;
import com.hoainhi.sportfields.entity.User;
import com.hoainhi.sportfields.service.CloudinaryService;
import com.hoainhi.sportfields.service.FacilityService;
import com.hoainhi.sportfields.service.WardSevice;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("facility")
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

    @PostMapping("/save")
    public String saveFacility(@ModelAttribute FacilityDTO facilityDTO, @RequestParam("image") MultipartFile image, HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        if(image != null && !image.isEmpty()){
            String imglUrl = cloudinaryService.uploadFile(image);
            facilityDTO.setImg_url(imglUrl);

        }
        if (facilityDTO.getId_facility() == null){
            facilityService.addfacility(facilityDTO, user);

        }else {
            facilityService.updateFacility(user, facilityDTO);
        }
        return "redirect:/facility/facilities_owner";

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

   @GetMapping("/{id}/approve")
    public String approve(@PathVariable Long id){
         facilityService.approve(id);
         return "admin/facilities/Facilities";
    }

    @GetMapping("edit/{id}")
    public String editFacility(@PathVariable Long id, Model model){

        Facility facility = facilityService.findById(id);

        FacilityDTO dto = new FacilityDTO();

        BeanUtils.copyProperties(facility, dto);

        dto.setIsEdit(true);

        model.addAttribute("facilityDTO", dto);
        model.addAttribute("wards", wardSevice.getDaNangWards());

        return "owner/facility/Registration";
    }

    @GetMapping("/detail/{id}")
    @ResponseBody
    public FacilityDTO getFacilityDetail(@PathVariable Long id) {
        return facilityService.getFacilityById(id);
    }


}
