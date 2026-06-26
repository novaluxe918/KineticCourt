package com.hoainhi.sportfields.dto;


import lombok.Data;

import java.util.List;

@Data
public class ProvinceDTO {
    private Integer code;
    private String name;
    private List<WardsDTO> wards;
}
