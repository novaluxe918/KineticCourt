package com.hoainhi.sportfields.dto;

import com.hoainhi.sportfields.enums.FaciStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class FacilityDTO {
 private Long id_facility;
   private String name_facility;
    private String address;
    private String description;
    private String phone;
    private String wards;
    private String img_url;
    private FaciStatus status;
    private LocalTime open_time;
    private LocalTime close_time;
    private Boolean isEdit = false;
}
