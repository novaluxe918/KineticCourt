package com.hoainhi.sportfields.dto;

import com.hoainhi.sportfields.validation.ValidDateRange;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data

@ValidDateRange
public class ScheduleDTO {
    @NotNull(message = "Vui lòng chọn sân")
    private Long id_court;

    @NotNull(message = "Vui lòng chọn ngày bắt đầu")
    @FutureOrPresent(message = "Ngày bắt đầu không được ở quá khứ")
    private LocalDate date_start;

    @NotNull(message = "Vui lòng chọn ngày kết thúc")
    @FutureOrPresent(message = "Ngày kết thúc không được ở quá khứ")
    private LocalDate date_end;

    @Valid
    private List<ScheduleDetailDTO> scheduleDetails;
}
