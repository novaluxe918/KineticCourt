package com.hoainhi.sportfields.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "schedule_details")
public class ScheduleDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate time_start;
    private LocalDate time_end;

    @ManyToOne
    @JoinColumn(name = "id_schedule", nullable = false)
    private Schedule schedule;
}
