package com.hoainhi.sportfields.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_service;

    @Column(columnDefinition = "varchar(100) not null")
    private String title;

    private double price;

    @Column(columnDefinition = "varchar(15)")
    private String unit;

    @ManyToOne
    @JoinColumn(name = "id_facility", nullable = false)
    private Facility facility;

    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER)
    private List<BookingService> bookingService;
}
