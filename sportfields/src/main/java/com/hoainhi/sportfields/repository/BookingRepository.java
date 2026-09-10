package com.hoainhi.sportfields.repository;

import com.hoainhi.sportfields.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("""
        SELECT b
        FROM Booking b
        WHERE b.user.id = :userId
        ORDER BY b.booking_date DESC
    """)
    List<Booking> findBookingHistory(@Param("userId") Long userId);
    @Query("""
        SELECT COUNT(b) > 0
        FROM Booking b
        JOIN b.bookingDetail bd
        WHERE b.booking_date = :bookingDate
        AND bd.scheduleDetails.id = :scheduleDetailId
    """)
    boolean existsBooking(
            @Param("bookingDate") LocalDate bookingDate,
            @Param("scheduleDetailId") Long scheduleDetailId
    );

    @Query("""
        SELECT DISTINCT b
        FROM Booking b
        JOIN b.bookingDetail bd
        JOIN bd.scheduleDetails sd
        JOIN sd.schedule s
        JOIN s.court c
        JOIN c.facility f
        WHERE f.user.id = :ownerId
        ORDER BY b.booking_date DESC
    """)
    List<Booking> findByCourtOwnerId(Long ownerId);

}
