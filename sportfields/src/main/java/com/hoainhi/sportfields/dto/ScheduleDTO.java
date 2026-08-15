package com.hoainhi.sportfields.dto;

import com.hoainhi.sportfields.validation.ValidDateRange;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data

@ValidDateRange
public class ScheduleDTO {
    private Long id;

    @NotNull(message = "Vui lòng chọn sân")
    private Long id_court;

    @NotNull(message = "Vui lòng chọn cơ sở")
    private Long facility_id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Vui lòng chọn ngày bắt đầu")
    @FutureOrPresent(message = "Ngày bắt đầu không được ở quá khứ")
    private LocalDate date_start;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Vui lòng chọn ngày kết thúc")
    @FutureOrPresent(message = "Ngày kết thúc không được ở quá khứ")
    private LocalDate date_end;

    private Boolean isEdit = false;

    @Valid
    private List<ScheduleDetailDTO> scheduleDetails = new ArrayList<>();
}
