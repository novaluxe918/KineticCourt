package com.hoainhi.sportfields.repository;

import com.hoainhi.sportfields.entity.ScheduleDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ScheduleDetailRepository extends JpaRepository<ScheduleDetails, Long> {
    List<ScheduleDetails> findBySchedule_Court_Facility_Id(Long facilitId);

    @Query(value = """
        SELECT sd.*
        FROM schedule_details sd
        JOIN schedules s
            ON sd.id_schedule = s.id_schedule
        JOIN courts c
            ON s.id_court = c.id_court
        WHERE c.id_court = ?1
          AND ?2 BETWEEN s.date_start AND s.date_end
        """, nativeQuery = true)
    List<ScheduleDetails> findByTimeScheduleDetails(
            Long courtId,
            LocalDate date
    );

    @Query(value = """
    SELECT sd.*
    FROM schedule_details sd
    JOIN schedules s
        ON sd.id_schedule = s.id_schedule
    JOIN courts c
        ON s.id_court = c.id_court
    JOIN facilities f
        ON c.facility_id = f.id_facility
    WHERE f.owner_id = ?1
    """, nativeQuery = true)
    List<ScheduleDetails> findByOwner(Long ownerId);

    List<ScheduleDetails> findByIdIn(List<Long> ids);

}
