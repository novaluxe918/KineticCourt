package com.hoainhi.sportfields.entity;

import com.hoainhi.sportfields.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "facilities")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_facility;
    @Column(columnDefinition = "varchar(100) not null")
    private String name_facility;

    @Column(nullable = false)
    private String address;


    @Column(columnDefinition = " text ")
    private String description;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String wards;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String img_url;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "facility", fetch = FetchType.EAGER)
    private List<Court> court;

    @OneToMany(mappedBy = "facility", fetch = FetchType.EAGER)
    private List<Service> service;

    @OneToMany(mappedBy = "facility", fetch = FetchType.EAGER)
    private List<Review> review;
}
