package com.hoainhi.sportfields.dto;

import com.hoainhi.sportfields.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourtDTO {
    private Long id;

    @NotBlank(message = "Vui lòng nhập tên sân")
    private String name_court;

    @NotNull(message = "Trạng thái sân không được để trống")
    private Status status;

    @NotNull(message = "Vui lòng chọn cơ sở")
    private Long facility_id;

    private Boolean isEdit = false;
}
