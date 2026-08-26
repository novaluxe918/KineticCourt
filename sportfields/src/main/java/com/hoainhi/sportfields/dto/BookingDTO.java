package com.hoainhi.sportfields.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDTO {
    private Double total;
    private LocalDate booking_date;
}
