package com.hoainhi.sportfields.entity;

import com.hoainhi.sportfields.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_booking")
    private Long id;

    private Double total;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDate booking_date;

    @OneToMany(mappedBy = "booking", fetch = FetchType.EAGER)
    private List<BookingDetails> bookingDetail;

    @OneToMany(mappedBy = "booking", fetch = FetchType.EAGER)
    private List<BookingService> bookingService;

    @OneToOne(mappedBy = "booking")
    private Payment payment;
}
