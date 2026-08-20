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
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) Long facilityId,
            HttpSession session) {

        // 1. Lấy user đang login
        User user = (User) session.getAttribute("loginUser");

        Pageable pageable = PageRequest.of(page, size);


        if (user == null) {
            return "redirect:/login";
        }

        // 2. Load danh sách facility của owner
        List<Facility> facilities =
                facilitySeviceimpl.getFacilityByOwner(user.getId());


        Page<Court> courts;

        if (facilityId == null) {
            // chưa chọn facility → lấy tất cả court của owner
            courts = courtService.getCourtByOwnerPaging(user.getId(), pageable);
        } else {

            courts = courtService.getCourtByFacilityPaging(facilityId, pageable);
        }




        // 6. đẩy dữ liệu ra view
        model.addAttribute("facilities", facilities);
        model.addAttribute("courts", courts);
        model.addAttribute("selectedFacility", facilityId);
        model.addAttribute("currentUrl", request.getRequestURI());

        return "owner/courts/Court";
    }

    @GetMapping("/add")
    public String addCourt( Model model, HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        List<Facility> facilities = facilitySeviceimpl.getFacilityByOwner(user.getId());

        model.addAttribute("court", new CourtDTO());
         model.addAttribute("facilities", facilities);

        return "owner/courts/AddCourt";
    }

    @PostMapping("/save")
    public String saveCourt(@Valid @ModelAttribute("court") CourtDTO courtDTO,BindingResult result, Model model){
        if(result.hasErrors()){
           model.addAttribute("facilities", facilitySeviceimpl.getApproved());
            return "owner/courts/AddCourt";
        }
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
        if(court.getFacility() != null){
           courtDTO.setFacility_id(court.getFacility().getId());

        }
        courtDTO.setIsEdit(true);
        model.addAttribute("facilities", facilitySeviceimpl.getApproved());
        model.addAttribute("court", courtDTO);
        return "owner/courts/AddCourt";
    }
}
