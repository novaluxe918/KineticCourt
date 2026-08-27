package com.hoainhi.sportfields.service.impl;

import com.hoainhi.sportfields.entity.BookingDetails;
import com.hoainhi.sportfields.repository.BookingDetailRepository;
import com.hoainhi.sportfields.service.BookingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingDetailServiceImpl implements BookingDetailsService {

    @Autowired
    private BookingDetailRepository bookingDetailRepository;
    @Override
    public BookingDetails save(BookingDetails bookingDetails) {
        return bookingDetailRepository.save(bookingDetails);
    }
}
