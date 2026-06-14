package com.hoainhi.sportfields.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "paymets")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_payment;
    private String payment_method;
    private LocalDate payment_date;
    private String status;
    private String transaction_code;
    private double amount;

    @OneToOne
    @JoinColumn(name = "id_booking")
    private Booking booking;
}
