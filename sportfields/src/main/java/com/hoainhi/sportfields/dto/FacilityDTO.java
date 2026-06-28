package com.hoainhi.sportfields.dto;

import com.hoainhi.sportfields.enums.FaciStatus;
import lombok.Data;

@Data
public class FacilityDTO {
   private String name_facility;
    private String address;
    private String description;
    private String phone;
    private String wards;
    private String img_url;
    private FaciStatus status;
}
