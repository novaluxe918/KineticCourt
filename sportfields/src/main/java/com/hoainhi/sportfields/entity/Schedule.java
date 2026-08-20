package com.hoainhi.sportfields.entity;

import com.hoainhi.sportfields.enums.ScheduleStatus;
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
    @Column(name = "id_schedule")
    private Long id;

    private LocalDate date_start;

    private LocalDate date_end;

    @ManyToOne
    @JoinColumn(name = "id_court", nullable = false)
    private Court court;



    @OneToMany(mappedBy = "schedule", fetch = FetchType.EAGER,  cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ScheduleDetails> scheduleDetails;
}
