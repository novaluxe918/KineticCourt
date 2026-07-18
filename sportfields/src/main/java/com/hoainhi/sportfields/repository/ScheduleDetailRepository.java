package com.hoainhi.sportfields.repository;

import com.hoainhi.sportfields.entity.ScheduleDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleDetailRepository extends JpaRepository<ScheduleDetails, Long> {
}
