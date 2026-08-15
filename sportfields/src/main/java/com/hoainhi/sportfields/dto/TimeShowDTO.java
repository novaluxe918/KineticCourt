package com.hoainhi.sportfields.dto;

import com.hoainhi.sportfields.enums.ScheduleStatus;
import lombok.Data;

@Data
public class TimeShowDTO {
    private String time;
    private ScheduleStatus status;
}
