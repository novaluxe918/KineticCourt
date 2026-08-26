package com.hoainhi.sportfields.service.impl;

import com.hoainhi.sportfields.dto.BookingDTO;
import com.hoainhi.sportfields.entity.Booking;
import com.hoainhi.sportfields.entity.User;
import com.hoainhi.sportfields.repository.BookingRepository;
import com.hoainhi.sportfields.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceimpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public Booking saveBooking(BookingDTO bookingDTO, User user) {
        Booking booking = new Booking();
        booking.setTotal(bookingDTO.getTotal());
        booking.setBooking_date(bookingDTO.getBooking_date());
        booking.setUser(user);
        return bookingRepository.save(booking);
    }
}
