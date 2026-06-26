package com.hoainhi.sportfields.service;

import com.hoainhi.sportfields.dto.ProvinceDTO;
import com.hoainhi.sportfields.dto.WardsDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WardSevice {
    private RestTemplate template = new RestTemplate();
    public List<WardsDTO> getDaNangWards(){
        String url = "https://provinces.open-api.vn/api/v2/p/48?depth=2";

        ProvinceDTO provinceDTO = template.getForObject(url, ProvinceDTO.class);
        return provinceDTO.getWards();
    }
}
