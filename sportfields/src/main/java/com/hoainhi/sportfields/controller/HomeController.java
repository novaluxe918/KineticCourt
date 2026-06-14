package com.hoainhi.sportfields.controller;

import com.hoainhi.sportfields.dto.AccountDTO;
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


    @PostMapping("/register")
    public ModelAndView registerUser(@Valid  @ModelAttribute AccountDTO accountDTO , BindingResult result, ModelMap model){
        if(result.hasErrors()){
            return new ModelAndView("client/auth/Register", model);
        }
        accountSercive.registerUser(accountDTO);

        return new ModelAndView("forward:client/auth/Login", model);
    }
}
