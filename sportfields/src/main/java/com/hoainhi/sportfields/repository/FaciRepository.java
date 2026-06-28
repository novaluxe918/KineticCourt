package com.hoainhi.sportfields.repository;

import com.hoainhi.sportfields.entity.Facility;
import com.hoainhi.sportfields.entity.User;
import com.hoainhi.sportfields.enums.FaciStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FaciRepository extends JpaRepository<Facility, Long> {
    List<Facility> findByUser_Id(Long userId);
    List<Facility> findByStatus(FaciStatus status);
}
