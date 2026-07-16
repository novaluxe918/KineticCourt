package com.hoainhi.sportfields.dto;

import com.hoainhi.sportfields.entity.Facility;
import lombok.Data;

@Data
public class ServiceDTO {
    private Long id;
    private String title;
    private double price;
    private String unit;
    private Long facilityId;
    private String description;
    private boolean isEdit = false;

}
