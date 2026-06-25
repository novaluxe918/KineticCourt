package com.hoainhi.sportfields.controller;

import com.hoainhi.sportfields.dto.AccountDTO;
import com.hoainhi.sportfields.entity.User;
import com.hoainhi.sportfields.enums.Role;
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

    @RequestMapping("/login")
    public String showLogin(Model model){
        model.addAttribute("accountDTO", new AccountDTO());
        return "client/auth/Login";
    }

    @RequestMapping("/register")
    public String showRegister(Model model, @RequestParam(defaultValue = "User") Role role){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setRole(role);
        model.addAttribute("accountDTO", accountDTO);
        return "client/auth/Register";
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@Valid @ModelAttribute AccountDTO accountDTO , BindingResult result, ModelMap model){
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
            return new ModelAndView("client/auth/Register", model);
        }
        accountSercive.registerUser(accountDTO);

        return new ModelAndView("client/Home", model);
    }

    @PostMapping("/login")
    public ModelAndView loginUser(@Valid @ModelAttribute AccountDTO accountDTO, BindingResult result, ModelMap modelMap,  HttpSession session){
        User user = accountSercive.loginUser(accountDTO);
        if(user == null){
            modelMap.addAttribute("message", "Email không tồn tại");
            return new ModelAndView("client/auth/Login", modelMap);
        }
        session.setAttribute("loginUser", user);
        if(user.getRole() == Role.Admin){
            return new ModelAndView("admin/dashboard/Dashboard");

        }
        if(user.getRole() == Role.Owner){
            return new ModelAndView("owner/dashboard/Dashboard");

        }
        return new ModelAndView("client/Home");
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "client/Home";
    }
}
