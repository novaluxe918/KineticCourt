package com.hoainhi.sportfields.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.EnableMBeanExport;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "bookingDetails")
public class BookingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bkDetail")
   private long id;
   private LocalDate booking_date;

    @ManyToOne
    @JoinColumn(name = "id_booking", nullable = false)
    private Booking booking;

    @ManyToOne()
    @JoinColumn(name = "id_schedule", nullable = false)
    private Schedule schedule;
}
