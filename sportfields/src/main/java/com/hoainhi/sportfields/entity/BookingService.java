package com.hoainhi.sportfields.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bookingServices")
public class BookingService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bkService")
    private Long id;
    private Double price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "id_booking", nullable = false)
    @JsonIgnore
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "id_service", nullable = false)
    private Services service;
}
