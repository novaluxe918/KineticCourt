package com.hoainhi.sportfields.service.impl;

import com.hoainhi.sportfields.entity.BookingService;
import com.hoainhi.sportfields.repository.BookingServiceRepository;

import com.hoainhi.sportfields.service.BookingServiceItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceItemServiceImpl implements BookingServiceItemService {
    @Autowired
    private BookingServiceRepository bookingServiceRepository;
    @Override
    public BookingService save(BookingService bookingService) {
        return bookingServiceRepository.save(bookingService);
    }
}
