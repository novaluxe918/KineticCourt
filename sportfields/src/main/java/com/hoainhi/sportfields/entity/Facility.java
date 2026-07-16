package com.hoainhi.sportfields.entity;

import com.hoainhi.sportfields.enums.FaciStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@Table(name = "facilities")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_facility")
    private Long id;
    @Column(columnDefinition = "varchar(100) not null")
    private String name_facility;

    @Column(nullable = false)
    private String address;


    @Column(columnDefinition = " text ")
    private String description;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String wards;

    @Enumerated(EnumType.STRING)
    private FaciStatus status;

    private String img_url;

    private LocalTime open_time;

    private LocalTime close_time;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "facility", fetch = FetchType.LAZY)
    private List<Court> court;

    @OneToMany(mappedBy = "facility", fetch = FetchType.EAGER)
    private List<Services> service;

    @OneToMany(mappedBy = "facility", fetch = FetchType.EAGER)
    private List<Review> review;
}
