package com.hoainhi.sportfields.repository;

import com.hoainhi.sportfields.entity.Services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ServiceRepository extends JpaRepository<Services, Long> {
    Page<Services> findByTitleContaining(String name, Pageable pageable);
    Page<Services> findByFacility_Id(Long facilityId,Pageable pageable);
}
