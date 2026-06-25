package com.hoainhi.sportfields.entity;

import com.hoainhi.sportfields.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "courts")
public class Court {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_court;

    @Column(columnDefinition = "varchar(100) not null")
    private String name_court;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "facility_id", nullable = false)
    private Facility facility;

    @OneToMany(mappedBy = "court", fetch = FetchType.EAGER)
    private List<Schedule> schedules;


}
