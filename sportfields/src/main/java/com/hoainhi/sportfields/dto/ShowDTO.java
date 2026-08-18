package com.hoainhi.sportfields.dto;

import com.hoainhi.sportfields.entity.Court;
import lombok.Data;

import java.util.List;

@Data
public class ShowDTO {
    List<TimeShowDTO> timeShowDTOS;
    Court court;
    private List<BookingBlockDTO> bookingBlocks;
}
