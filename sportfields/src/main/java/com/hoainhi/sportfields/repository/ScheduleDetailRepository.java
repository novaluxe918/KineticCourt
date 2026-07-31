package com.hoainhi.sportfields.repository;

import com.hoainhi.sportfields.entity.ScheduleDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleDetailRepository extends JpaRepository<ScheduleDetails, Long> {
    List<ScheduleDetails> findBySchedule_Court_Facility_Id(Long facilitId);
}
