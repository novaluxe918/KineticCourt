package com.hoainhi.sportfields.repository;

import com.hoainhi.sportfields.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

}
