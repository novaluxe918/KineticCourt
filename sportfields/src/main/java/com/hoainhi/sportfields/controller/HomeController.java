package com.hoainhi.sportfields.controller;

import com.hoainhi.sportfields.dto.AccountDTO;
import com.hoainhi.sportfields.entity.User;
import com.hoainhi.sportfields.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {

    @Autowired
    private AccountService accountSercive;

    @RequestMapping("/home")
    public String showHome(Model model){
        model.addAttribute("accountDTO", new AccountDTO() );
        return "client/auth/Register";
    }

    @RequestMapping("/login")
    public String showLogin(Model model){
        model.addAttribute("accountDTO", new AccountDTO());
        return "client/auth/Login";
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@Valid  @ModelAttribute AccountDTO accountDTO , BindingResult result, ModelMap model){
        if(accountSercive.existsByEmail(accountDTO.getEmail())){
            result.rejectValue(
                    "email",
                    "error.accountDTO",
                    "Email da duoc su dung"
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

        return new ModelAndView("forward:client/auth/Login", model);
    }

    @PostMapping("/login")
    public ModelAndView loginUser(@Valid @ModelAttribute AccountDTO accountDTO, BindingResult result, ModelMap modelMap){
        User user = accountSercive.loginUser(accountDTO);
        if(user == null){
            modelMap.addAttribute("message", "Email khong ton tai");
            return new ModelAndView("client/auth/Login", modelMap);
        }
        return new ModelAndView("client/Home", modelMap);
    }
}
