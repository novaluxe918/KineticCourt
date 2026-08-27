package com.hoainhi.sportfields.service;

import com.hoainhi.sportfields.entity.BookingDetails;
import org.springframework.stereotype.Service;

@Service
public interface BookingDetailsService {
    BookingDetails save(BookingDetails bookingDetails);
}
