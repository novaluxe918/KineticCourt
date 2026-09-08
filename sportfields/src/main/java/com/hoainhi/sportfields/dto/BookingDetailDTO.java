package com.hoainhi.sportfields.dto;

import com.hoainhi.sportfields.enums.BookingStatus;
import lombok.Data;

import java.util.List;

@Data
public class BookingDetailDTO {
    private Long id;
    private BookingStatus status;
    private String name_facility;
    private Double total;
    private String address;

    private String name_court;

    private String time_start;

    private String time_end;

    private List<BookingServiceDTO> services;
}
