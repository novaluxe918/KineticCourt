package com.hoainhi.sportfields.controller;

import com.hoainhi.sportfields.dto.AccountDTO;
import com.hoainhi.sportfields.entity.User;
import com.hoainhi.sportfields.enums.Role;
import com.hoainhi.sportfields.enums.UserStatus;
import com.hoainhi.sportfields.service.AccountService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {
    @Autowired
    private AccountService accountSercive;

    @GetMapping("/login")
    public String showLogin(@RequestParam(required = false) String error,Model model){
        if ("locked".equals(error)) {
            model.addAttribute(
                    "message",
                    "Tài khoản của bạn đã bị khóa."
            );
        }

        if ("google".equals(error)) {
            model.addAttribute(
                    "message",
                    "Tài khoản Google chưa được đăng ký."
            );
        }
        model.addAttribute("accountDTO", new AccountDTO());

        return "client/auth/Login";
    }

    @GetMapping("/register")
    public String showRegister(Model model, @RequestParam(defaultValue = "User") Role role){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setRole(role);
        model.addAttribute("accountDTO", accountDTO);
        return "client/auth/Register";
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@Valid @ModelAttribute AccountDTO accountDTO , BindingResult result, ModelMap model, HttpSession session){


        if(accountSercive.existsByEmail(accountDTO.getEmail())){
            result.rejectValue(
                    "email",
                    "error.accountDTO",
                    "Email đã được sử dụng"
            );
        }
        if(!accountDTO.getPassword().equals(accountDTO.getConfirmPassword())){
            result.rejectValue(
                    "confirmPassword",
                    "error.accountDTO",
                    "Mật khẩu nhập lại không khớp"
            );
        }
        if(result.hasErrors()){
            model.addAttribute("accountDTO", accountDTO);

            return new ModelAndView("client/auth/Register", model);
        }

      User user =  accountSercive.registerUser(accountDTO);
        session.setAttribute("loginUser", user);
        if(user.getRole() == Role.Owner){
            return new ModelAndView("owner/facility/Facility", model);
        }
        return new ModelAndView("client/home/Home", model);
    }

    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute AccountDTO accountDTO, BindingResult result, ModelMap modelMap, HttpSession session){
        User user = accountSercive.loginUser(accountDTO);
        if(user == null){
            modelMap.addAttribute("message", "Email không tồn tại");
            return "client/auth/Login";
        }
        if(user.getStatus() == UserStatus.LOCKED){
           modelMap.addAttribute("message", "Tài khoản của bạn đã bị khóa vui lòng liên hệ quản trị viên");
            return "client/auth/Login";

        }        session.setAttribute("loginUser", user);
        if(user.getRole() == Role.Admin){
            return "redirect:/admin/dashboard";

        }
        if(user.getRole() == Role.Owner){
            return "redirect:/facility/facilities_owner";

        }
        return "redirect:/home";
    }


}
