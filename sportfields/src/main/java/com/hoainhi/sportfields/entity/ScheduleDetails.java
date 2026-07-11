package com.hoainhi.sportfields.entity;

import com.hoainhi.sportfields.enums.ScheduleStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "schedule_details")
public class ScheduleDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_schedule")
    private Long id;

    private LocalTime time_start;
    private LocalTime time_end;

    private double price;

    private ScheduleStatus status;
    @ManyToOne
    @JoinColumn(name = "id_scheduleDetail", nullable = false)
    private Schedule schedule;
}
