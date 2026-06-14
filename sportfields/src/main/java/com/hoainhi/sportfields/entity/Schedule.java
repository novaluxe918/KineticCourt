package com.hoainhi.sportfields.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_schedule;



    private LocalDate date_start;

    private LocalDate date_end;

    private double price;
    private LocalDate time_start;
    private LocalDate time_end;

    @ManyToOne
    @JoinColumn(name = "id_court", nullable = false)
    private Court court;

    @OneToMany(mappedBy = "schedule", fetch = FetchType.EAGER)
    private List<BookingDetails> bookingDetails;
}
