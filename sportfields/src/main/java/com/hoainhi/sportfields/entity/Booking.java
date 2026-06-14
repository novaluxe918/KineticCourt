package com.hoainhi.sportfields.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_booking;

    private double total;
    private String status;
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "booking", fetch = FetchType.EAGER)
    private List<BookingDetails> bookingDetail;

    @OneToMany(mappedBy = "booking", fetch = FetchType.EAGER)
    private List<BookingService> bookingService;

    @OneToOne(mappedBy = "booking")
    private Payment payment;
}
