package com.hoainhi.sportfields.service;

import com.hoainhi.sportfields.entity.BookingService;
import org.springframework.stereotype.Service;

@Service
public interface BookingServiceItemService {
    BookingService save(BookingService bookingService);
}
