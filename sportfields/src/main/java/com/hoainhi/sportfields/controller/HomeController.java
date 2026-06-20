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



    @RequestMapping("/home")
    public String showHome(Model model){
        model.addAttribute("accountDTO", new AccountDTO() );
        return "client/Home";
    }

    @RequestMapping("/dashboard")
    public String showDasboard(Model model){
        return "admin/dashboard/Dashboard";
    }


}

