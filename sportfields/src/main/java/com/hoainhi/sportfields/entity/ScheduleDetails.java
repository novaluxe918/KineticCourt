package com.hoainhi.sportfields.entity;

import com.hoainhi.sportfields.enums.ScheduleStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@Table(name = "schedule_details")
public class ScheduleDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scheduleDetail")
    private Long id;

    private LocalTime time_start;
    private LocalTime time_end;

    private Double price;

    @Enumerated(EnumType.STRING)
    private ScheduleStatus status;
    @ManyToOne
    @JoinColumn(name = "id_schedule", nullable = false)
    private Schedule schedule;

    @OneToMany(mappedBy = "scheduleDetails", fetch = FetchType.EAGER)
    private List<BookingDetails> bookingDetails;
}
