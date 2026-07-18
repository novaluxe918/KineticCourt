package com.hoainhi.sportfields.dto;

import lombok.Data;

import java.util.List;

@Data
public class CalendarCourtDTO {
    private Long courtId;
    private String courtName;
    private List<ScheduleDetailDTO> slots;
}
