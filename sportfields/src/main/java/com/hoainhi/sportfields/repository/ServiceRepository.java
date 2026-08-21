package com.hoainhi.sportfields.repository;

import com.hoainhi.sportfields.entity.Services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ServiceRepository extends JpaRepository<Services, Long> {
    Page<Services> findByTitleContaining(String name, Pageable pageable);


    Page<Services> findByFacility_Id(Long facilityId,Pageable pageable);

    @Query(value = """
    SELECT se.*
    FROM services AS se
    JOIN facilities AS f
        ON se.id_facility = f.id_facility
    WHERE f.owner_id = ?1
    """,
            countQuery = """
    SELECT COUNT(*)
    FROM services AS se
    JOIN facilities AS f
        ON se.id_facility = f.id_facility
    WHERE f.owner_id = ?1
    """,
            nativeQuery = true)
    Page<Services> findByOwner(
            Long ownerId,
            Pageable pageable
    );

    @Query(value = """
    SELECT se.*
    FROM services AS se
    JOIN facilities AS f
        ON se.id_facility = f.id_facility
    WHERE f.id_facility = ?1
      AND f.owner_id = ?2
    """,
            countQuery = """
    SELECT COUNT(*)
    FROM services AS se
    JOIN facilities AS f
        ON se.id_facility = f.id_facility
    WHERE f.id_facility = ?1
      AND f.owner_id = ?2
    """,
            nativeQuery = true)
    Page<Services> findByFacilityAndOwner(
            Long facilityId,
            Long ownerId,
            Pageable pageable
    );


}
