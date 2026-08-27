package com.hoainhi.sportfields.service.impl;

import com.hoainhi.sportfields.dto.BookingDTO;
import com.hoainhi.sportfields.entity.Booking;
import com.hoainhi.sportfields.entity.BookingDetails;
import com.hoainhi.sportfields.entity.ScheduleDetails;
import com.hoainhi.sportfields.entity.User;
import com.hoainhi.sportfields.enums.BookingStatus;
import com.hoainhi.sportfields.repository.BookingRepository;
import com.hoainhi.sportfields.service.BookingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingServiceimpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }


}
