package com.hoainhi.sportfields.repository;

import com.hoainhi.sportfields.entity.Court;

import com.hoainhi.sportfields.enums.FaciStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourtRepository extends JpaRepository<Court, Long> {

     List<Court> findByFacility_IdAndFacility_Status(
             Long facilityId,
             FaciStatus status
     );
     List<Court> findByFacility_Id(Long facilityId);
     List<Court> findByFacility_User_IdAndFacility_Status(
             Long ownerId,
             FaciStatus status
     );

     Page<Court> findPageByFacility_User_IdAndFacility_Status(
             Long ownerId,
             FaciStatus status,
             Pageable pageable
     );


     Page<Court> findPageByFacility_IdAndFacility_Status(
             Long facilityId,
             FaciStatus status,
             Pageable pageable
     );
}
