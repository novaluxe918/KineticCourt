package com.hoainhi.sportfields.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "services")
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_service")
    private Long id;

    @Column(columnDefinition = "varchar(100) not null")
    private String title;

    private double price;

    @Column(columnDefinition = "varchar(15)")
    private String unit;

    @Column(columnDefinition = " text ")
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_facility", nullable = false)
    private Facility facility;

    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER)
    private List<BookingService> bookingService;
}
