package com.hoainhi.sportfields.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class BookingBlockDTO {
    private LocalTime startTime;
    private LocalTime endTime;
    private int startColumn;
    private int slotCount;
    private Double price;

}
