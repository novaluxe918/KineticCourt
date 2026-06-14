package com.hoainhi.sportfields.entity;

import com.hoainhi.sportfields.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import org.springframework.web.bind.annotation.Mapping;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(columnDefinition = "varchar(100) not null")
    private String name_user;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private String avatar;
    private String phone;
    private LocalDate createdAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Facility> facilities;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Booking> bookings;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Review> review;
}
