package com.hoainhi.sportfields.dto;

import com.hoainhi.sportfields.enums.Status;
import lombok.Data;

@Data
public class CourtDTO {
    private Long id;
    private String name_court;
    private Status status;
    private Long facility_id;
    private Boolean isEdit = false;
}
