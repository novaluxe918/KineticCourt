package com.hoainhi.sportfields.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ScheduleDTO {
    private Long id_court;
    private LocalDate date_start;
    private LocalDate date_end;
    private List<ScheduleDetailDTO> scheduleDetails;
}
