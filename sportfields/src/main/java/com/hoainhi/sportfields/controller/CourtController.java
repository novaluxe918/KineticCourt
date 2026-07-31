package com.hoainhi.sportfields.controller;

import com.hoainhi.sportfields.dto.CourtDTO;
import com.hoainhi.sportfields.entity.Court;
import com.hoainhi.sportfields.entity.Facility;
import com.hoainhi.sportfields.entity.User;
import com.hoainhi.sportfields.enums.Status;
import com.hoainhi.sportfields.service.impl.CourtServiceImpl;
import com.hoainhi.sportfields.service.impl.FacilitySeviceimpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("court")
 public class CourtController {
    @Autowired
    private FacilitySeviceimpl facilitySeviceimpl;

    @Autowired
    private CourtServiceImpl courtService;
    @GetMapping("/court_owner")
    public String showCourt(
            HttpServletRequest request,
            Model model,
            @RequestParam(required = false) Long facilityId,
            HttpSession session) {

        // 1. Lấy user đang login
        User user = (User) session.getAttribute("loginUser");

        if (user == null) {
            return "redirect:/login";
        }

        // 2. Load danh sách facility của owner
        List<Facility> facilities =
                facilitySeviceimpl.getFacilityByOwner(user.getId());


        List<Court> courts;

        if (facilityId == null) {
            // chưa chọn facility → lấy tất cả court của owner
            courts = courtService.getCourtByOwner(user.getId());
        } else {

            courts = courtService.getCourtByFacility(facilityId);
        }


        if (courts == null) {
            courts = new ArrayList<>();
        }


        // 6. đẩy dữ liệu ra view
        model.addAttribute("facilities", facilities);
        model.addAttribute("court", courts);
        model.addAttribute("selectedFacility", facilityId);
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
        if(courtDTO.getId() == null){

            courtService.addCourt(courtDTO);

        }else {
            courtService.updateCourt(courtDTO);
        }
        return "redirect:/court/court_owner";

    }

    @GetMapping("delete/{id}")
    public String deleteCourt( @PathVariable Long id){
        courtService.delteteCourt(id);
        return "redirect:/court/court_owner";
    }

    @GetMapping("/courts/{facilityId}")
    @ResponseBody
    public List<Court> getCourts(@PathVariable Long facilityId){
        return courtService.getCourtByFacility(facilityId);
    }

    @GetMapping("/maintenance/{id}")
    public String maintenanceCourt(@PathVariable Long id){
        Court court = courtService.getById(id);

        if (court != null){
            court.setStatus(Status.MAINTENANCE);
            courtService.save(court);

        }
        return "redirect:/court/court_owner";
    }

    @GetMapping("/edit/{id}")
    public String editCourt(@PathVariable Long id, Model model){
        Court court = courtService.getById(id);
        CourtDTO courtDTO = new CourtDTO();
        BeanUtils.copyProperties(court, courtDTO);
        courtDTO.setIsEdit(true);
        model.addAttribute("court", courtDTO);
        return "owner/courts/AddCourt";
    }
}
