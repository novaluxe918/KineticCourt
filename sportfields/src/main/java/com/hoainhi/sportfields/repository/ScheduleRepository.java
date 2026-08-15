package com.hoainhi.sportfields.repository;

import com.hoainhi.sportfields.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
  Page<Schedule> findByCourt_Facility_User_Id(Long userId, Pageable pageable);

}
