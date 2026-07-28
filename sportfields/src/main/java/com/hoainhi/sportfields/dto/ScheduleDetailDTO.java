package com.hoainhi.sportfields.dto;

import com.hoainhi.sportfields.enums.ScheduleStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalTime;

@Data
public class ScheduleDetailDTO {

    private LocalTime time_start;
    private LocalTime time_end;

    @NotNull(message = "Giá tiền không được để trống")
    @Positive(message = "Giá tiền phải lớn hơn 0")
    private Double price;
    private ScheduleStatus status;
    private Integer rowStart;
    private Integer rowSpan;
}
