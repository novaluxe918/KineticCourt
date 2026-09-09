package com.hoainhi.sportfields.dto;

import com.hoainhi.sportfields.enums.BookingStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class BookingDetailDTO {
    private Long id;
    private BookingStatus status;
    private String name_facility;
    private Double total;
    private String address;
    private LocalDate booking_date;

    private String name_court;

    private LocalTime time_start;

    private LocalTime time_end;

    private List<BookingServiceDTO> services;
}
