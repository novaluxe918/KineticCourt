package com.hoainhi.sportfields.controller;

import com.hoainhi.sportfields.entity.User;
import com.hoainhi.sportfields.enums.Role;
import com.hoainhi.sportfields.service.UserService;
import com.hoainhi.sportfields.service.impl.FacilitySeviceimpl;
import com.hoainhi.sportfields.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private FacilitySeviceimpl facilitySeviceimpl;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession httpSession, Model model){

        User user = (User) httpSession.getAttribute("loginUser");

                if(user == null){
                   return "client/auth/Login";
                }
                if(user.getRole() != Role.Admin){
                    return "client/Home";
                }
                model.addAttribute("activePage", "dashboard");
                return "admin/dashboard/Dashboard";
    }

    @GetMapping("/usermanagement")
    public String showUserManage(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("activePage", "users");
        return "admin/usermanagement/Usermanagement";
    }

    @PostMapping("toggle/{id}")
    public String toggleStatus(@PathVariable Long id){
        userService.toggleUserStatus(id);
        return "redirect:/admin/usermanagement";
    }

    @GetMapping("/facilities")
    public String showFacilities(Model model){
        model.addAttribute("activePage", "facilities");
        model.addAttribute("facilities", facilitySeviceimpl.getAll());
        return "admin/facilities/Facilities";
    }

}
