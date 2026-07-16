package com.hoainhi.sportfields.dto;

import com.hoainhi.sportfields.enums.ScheduleStatus;
import lombok.Data;

import java.time.LocalTime;

@Data
public class ScheduleDetailDTO {

    private LocalTime time_start;
    private LocalTime time_end;
    private double price;
    private ScheduleStatus status;
}
