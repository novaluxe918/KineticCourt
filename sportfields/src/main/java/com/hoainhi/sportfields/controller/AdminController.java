package com.hoainhi.sportfields.controller;

import com.hoainhi.sportfields.entity.User;
import com.hoainhi.sportfields.enums.Role;
import com.hoainhi.sportfields.service.UserService;
import com.hoainhi.sportfields.service.impl.FacilitySeviceimpl;
import com.hoainhi.sportfields.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String showUserManage(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size, Model model){
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userService.getAllUsers(pageable);
        model.addAttribute("users", users.getContent());
        model.addAttribute("userPage", users);
        model.addAttribute("activePage", "users");
        return "admin/usermanagement/Usermanagement";
    }

    @PostMapping("toggle/{id}")
    public String toggleStatus(@PathVariable Long id){
        userService.toggleUserStatus(id);
        return "redirect:/admin/usermanagement";
    }



}
