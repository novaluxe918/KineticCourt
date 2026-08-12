package com.hoainhi.sportfields.controller;

import com.hoainhi.sportfields.dto.FacilityDTO;
import com.hoainhi.sportfields.dto.WardsDTO;
import com.hoainhi.sportfields.entity.Facility;
import com.hoainhi.sportfields.entity.Services;
import com.hoainhi.sportfields.entity.User;
import com.hoainhi.sportfields.service.CloudinaryService;
import com.hoainhi.sportfields.service.FacilityService;
import com.hoainhi.sportfields.service.WardSevice;
import com.hoainhi.sportfields.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @Autowired
    private ServiceImpl service;
    @GetMapping("/add")
    public String addFacility( Model model){
        model.addAttribute("facilityDTO" , new FacilityDTO());
        model.addAttribute("wards", wardSevice.getDaNangWards());
        return "owner/facility/Registration";
    }


    @PostMapping("/save")
    public String saveFacility(
            @Valid @ModelAttribute("facilityDTO") FacilityDTO facilityDTO,
            BindingResult result,
            @RequestParam("image") MultipartFile image,
            HttpSession session,
            Model model) {


        if (result.hasErrors()) {
            List<WardsDTO> wards = wardSevice.getDaNangWards();
            model.addAttribute("wards", wards);
            return "owner/facility/Registration";
        }

        User user = (User) session.getAttribute("loginUser");

        if (user == null) {
            return "redirect:/login";
        }

        // Upload ảnh nếu có
        if (image != null && !image.isEmpty()) {
            String imgUrl = cloudinaryService.uploadFile(image);
            facilityDTO.setImg_url(imgUrl);
        }


        if (facilityDTO.getId() == null) {
            facilityService.addfacility(facilityDTO, user);
        } else {
            facilityService.updateFacility(user, facilityDTO);
        }

        return "redirect:/facility/facilities_owner";
    }

    @GetMapping("/facilities_owner")
    public String listFacilities(Model model, HttpSession session, HttpServletRequest request){
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }

        List<Facility> facilities = facilityService.getFacilitiesByUser(loginUser);
        model.addAttribute("currentUrl", request.getRequestURI());
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

    @GetMapping("/delete/{id}")
    public String deleteFaci(@PathVariable Long id){
        facilityService.deleteFaci(id);
        return "redirect:/facility/facilities_owner";
    }

    @GetMapping("/view/{id}")
    public String detailFacility(@PathVariable Long id, Model model, Pageable pageable){
        FacilityDTO facility = facilityService.getFacilityById(id);
        Page<Services> page = service.findByFacility_Id(id, pageable );
        model.addAttribute("page", page);
        model.addAttribute("facility", facility);

        return "client/facility/Detail";
    }

    @GetMapping("/list")
    public String listFacility(Model model){
        List<Facility> facilities = facilityService.getApproved();
        model.addAttribute("facilities", facilities);
        return "client/facility/ListFacility";
    }
}
