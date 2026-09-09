package com.hoainhi.sportfields.dto;

import lombok.Data;

@Data
public class BookingServiceDTO {
    private Long serviceId;
    private int quantity;
    private String title;
    private Double price;
}
