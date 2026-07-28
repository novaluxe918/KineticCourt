package com.hoainhi.sportfields.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ScheduleDTO {
    private Long id_court;
    @NotNull(message = "Vui lòng chọn ngày bắt đầu")
    private LocalDate date_start;

    @NotNull(message = "Vui lòng chọn ngày kết thúc")
    private LocalDate date_end;
    private List<ScheduleDetailDTO> scheduleDetails;
}
