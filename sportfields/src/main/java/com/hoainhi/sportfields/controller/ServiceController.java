package com.hoainhi.sportfields.controller;

import com.hoainhi.sportfields.dto.ServiceDTO;
import com.hoainhi.sportfields.entity.User;

import com.hoainhi.sportfields.service.impl.FacilitySeviceimpl;
import com.hoainhi.sportfields.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("service")
public class ServiceController {
    @Autowired
    private FacilitySeviceimpl facilitySeviceimpl;

    @Autowired
    private ServiceImpl service;

    @GetMapping("/service_home")
    public String showService(Model model, HttpServletRequest request){
        model.addAttribute("currentUrl", request.getRequestURI());
        return "owner/services/Service";
    }

    @GetMapping("/add")
    public String addService(Model model, HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        model.addAttribute("service", new ServiceDTO());
        model.addAttribute("facilities", facilitySeviceimpl.getFacilityByOwner(user.getId()));
        return "owner/services/AddService";
    }

    @PostMapping("/save")
    public String saveService(@ModelAttribute ServiceDTO serviceDTO){
        service.addService(serviceDTO);
        return "redirect:/service/service_home";
    }

}
