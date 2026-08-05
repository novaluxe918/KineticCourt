package com.hoainhi.sportfields.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("booking")
public class BookingController {

    @GetMapping("/book")
    public String bookingClient(){
        return "client/booking/Booking";
    }
}
