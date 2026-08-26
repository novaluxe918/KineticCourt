package com.hoainhi.sportfields.repository;

import com.hoainhi.sportfields.entity.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDetailRepository extends JpaRepository<BookingDetails, Long> {
}
