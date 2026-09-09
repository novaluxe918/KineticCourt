package com.hoainhi.sportfields.service;

import com.hoainhi.sportfields.dto.BookingDetailDTO;
import com.hoainhi.sportfields.dto.ShowDTO;
import com.hoainhi.sportfields.entity.Booking;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface BookingServices {

    Booking saveBooking(Booking booking);
    List<Booking> getBookingHistory(Long userId);
    BookingDetailDTO getBookingDetail(Long id);
    List<ShowDTO> getShowBooking(Long facilityId, LocalDate date);

}
