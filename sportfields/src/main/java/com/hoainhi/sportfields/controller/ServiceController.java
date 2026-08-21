package com.hoainhi.sportfields.controller;

import ch.qos.logback.core.util.StringUtil;
import com.hoainhi.sportfields.dto.ServiceDTO;
import com.hoainhi.sportfields.entity.Facility;
import com.hoainhi.sportfields.entity.Services;
import com.hoainhi.sportfields.entity.User;

import com.hoainhi.sportfields.service.impl.FacilitySeviceimpl;
import com.hoainhi.sportfields.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("service")
public class ServiceController {
    @Autowired
    private FacilitySeviceimpl facilitySeviceimpl;

    @Autowired
    private ServiceImpl service;

    @GetMapping("/service_home")
    public String showService(Model model, HttpServletRequest request, HttpSession session,
                              @RequestParam (required = false) Long facilityId,
                              @RequestParam(name = "name", required = false) String name,
                              @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                              @RequestParam(value = "size", required = false, defaultValue = "5") int size){

        User user = (User) session.getAttribute("loginUser");

        List<Facility> facilities =
                facilitySeviceimpl.getFacilityByOwner(user.getId());
        Pageable pageable = PageRequest.of(page, size);
        Page<Services> servicesPage;

        if (facilityId != null) {

            servicesPage = service.getServiceByFacilityAndOwner(
                    facilityId,
                    user.getId(),
                    pageable
            );

        } else {

            servicesPage = service.getServiceByOwner(
                    user.getId(),
                    pageable
            );
        }


        model.addAttribute("selectFacility", facilityId);
        model.addAttribute("facilities", facilities);
        model.addAttribute("page", servicesPage);
        model.addAttribute("services", servicesPage.getContent());
        model.addAttribute("currentUrl", request.getRequestURI());
        return "owner/services/Service";
    }

    @GetMapping("/edit/{id}")
    public String editService(@PathVariable Long id, Model model, HttpSession session){
           User user = (User) session.getAttribute("loginUser");
           ServiceDTO serviceDTO = service.getServiceDTOById(id);
           serviceDTO.setEdit(true);
           model.addAttribute("service", serviceDTO);
        model.addAttribute("facilities", facilitySeviceimpl.getApproved());
        return "owner/services/AddService";
    }

    @GetMapping("/delete/{id}")
    public String deleteService(@PathVariable Long id){
        service.deleteService(id);
        return "redirect:/service/service_home";
    }

    @GetMapping("/add")
    public String addService(Model model, HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        model.addAttribute("service", new ServiceDTO());
        model.addAttribute("facilities", facilitySeviceimpl.getFacilityByOwner(user.getId()));
        return "owner/services/AddService";
    }

    @PostMapping("/save")
    public String saveService(@Valid  @ModelAttribute("service") ServiceDTO serviceDTO, BindingResult result, Model model){

        if(result.hasErrors()){
            model.addAttribute("facilities", facilitySeviceimpl.getApproved());
            return "owner/services/AddService";

        }
        if (serviceDTO.getId() == null){
            service.addService(serviceDTO);
        }else {
            service.updateService(serviceDTO);

        }
        return "redirect:/service/service_home";
    }

}
