package com.hoainhi.sportfields.service;

import com.hoainhi.sportfields.dto.CourtDTO;
import com.hoainhi.sportfields.entity.Court;
import org.springframework.stereotype.Service;


public interface CourtService {
    Court addCourt(CourtDTO courtDTO);
}
