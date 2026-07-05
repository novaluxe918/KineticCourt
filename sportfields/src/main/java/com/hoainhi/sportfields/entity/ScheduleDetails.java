package com.hoainhi.sportfields.entity;

import com.hoainhi.sportfields.enums.ScheduleStatus;
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

    private double price;

    private ScheduleStatus status;
    @ManyToOne
    @JoinColumn(name = "id_schedule", nullable = false)
    private Schedule schedule;
}
