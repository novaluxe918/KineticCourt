package com.hoainhi.sportfields.controller;

import com.hoainhi.sportfields.entity.User;
import com.hoainhi.sportfields.enums.Role;
import com.hoainhi.sportfields.service.impl.FacilitySeviceimpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    @Autowired
    private FacilitySeviceimpl facilitySeviceimpl;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession httpSession){

        User user = (User) httpSession.getAttribute("loginUser");

                if(user == null){
                   return "client/auth/Login";
                }
                if(user.getRole() != Role.Admin){
                    return "client/Home";
                }
                return "admin/dashboard/Dashboard";
    }

    @GetMapping("/usermanagement")
    public String showUserManage(){
        return "admin/usermanagement/Usermanagement";
    }

    @GetMapping("/facilities")
    public String showFacilities(Model model){
        model.addAttribute("facilities", facilitySeviceimpl.getPending());
        return "admin/facilities/Facilities";
    }

}
