package com.hoainhi.sportfields.dto;

import com.hoainhi.sportfields.entity.Services;
import com.hoainhi.sportfields.enums.FaciStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.pl.NIP;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class FacilityDTO {
 private Long id;

   @NotEmpty(message = "Không được để trống tên cơ sở ")
   private String name_facility;


   @NotEmpty(message = "Không được để trống địa chỉ " )
    private String address;

   @NotEmpty(message = "Bắt buộc nhập mô tả")
    private String description;

    @NotEmpty(message = "Không được để trống số điện thoại")
    private String phone;

    @NotEmpty(message = "Không được để trống phuường/xã")
    private String wards;


    private String img_url;

    private FaciStatus status;

    @NotNull(message = "Phải có giờ mở cửa")
    private LocalTime open_time;
 @NotNull(message = "Phải có giờ đóng cửa")
    private LocalTime close_time;
    private Boolean isEdit = false;

}
