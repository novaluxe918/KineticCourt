package com.hoainhi.sportfields.entity;

import com.hoainhi.sportfields.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "paymets")
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment")
    private Long id;
    private String payment_method;
    private LocalDate payment_date;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String transaction_code;
    private double amount;

    @OneToOne
    @JoinColumn(name = "id_booking")
    private Booking booking;
}
