package com.hoainhi.sportfields.service;

import com.hoainhi.sportfields.dto.BookingDTO;
import com.hoainhi.sportfields.entity.Booking;
import com.hoainhi.sportfields.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {

    Booking saveBooking(BookingDTO bookingDTO, User user);
}
