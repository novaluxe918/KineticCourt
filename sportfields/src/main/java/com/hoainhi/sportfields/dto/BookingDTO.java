package com.hoainhi.sportfields.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookingDTO {
    private Long facilityId;
    private List<Long> selectedSlots;
    private Double courtTotal;
    private Double serviceTotal;
    private Double total;
    private LocalDate booking_date;
}
