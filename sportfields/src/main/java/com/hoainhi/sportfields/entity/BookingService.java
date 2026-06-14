package com.hoainhi.sportfields.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bookingServices")
public class BookingService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_bkService;
    private double price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "id_booking", nullable = false)
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "id_service", nullable = false)
    private Service service;
}
