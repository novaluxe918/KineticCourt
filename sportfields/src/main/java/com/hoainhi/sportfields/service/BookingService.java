package com.hoainhi.sportfields.service;

import com.hoainhi.sportfields.dto.BookingDTO;
import com.hoainhi.sportfields.entity.Booking;
import com.hoainhi.sportfields.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {

    Booking saveBooking(Booking booking);

}
