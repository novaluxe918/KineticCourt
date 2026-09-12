package com.hoainhi.sportfields.dto;

import com.hoainhi.sportfields.enums.PaymentStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PaymentHistoryDTO {
    private Long paymentId;
    private String transaction_code;
    private PaymentStatus status;
    private Long bookingId;
    private Double amount;
    private String payment_method;
    private LocalDate payment_date;
    private String name_court;
    private String facility_name;

}
