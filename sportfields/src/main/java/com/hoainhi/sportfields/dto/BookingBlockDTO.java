package com.hoainhi.sportfields.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class BookingBlockDTO {
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private int startColumn;
    private int slotCount;
    private Double price;

}
