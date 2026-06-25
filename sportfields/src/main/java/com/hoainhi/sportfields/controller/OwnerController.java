package com.hoainhi.sportfields.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OwnerController {
    @GetMapping("/facilities_owner")
    public String facilities(){
        return "owner/facility/Facility";
    }
}
