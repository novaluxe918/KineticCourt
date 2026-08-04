package com.hoainhi.sportfields.dto;

import com.hoainhi.sportfields.entity.Facility;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ServiceDTO {
    private Long id;

    @NotBlank(message = "Tên dịch vụ không được để trống")
    private String title;

    @Positive(message = "Giá tiền phải lớn hơn 0")
    private double price;

    @NotBlank(message = "Đơn vị không được để trống")
    private String unit;

    @NotNull(message = "Vui lòng chọn cơ sở")
    private Long facilityId;

    private String description;
    private boolean isEdit = false;

}
