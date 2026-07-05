package com.hoainhi.sportfields.repository;

import com.hoainhi.sportfields.entity.Court;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtRepository extends JpaRepository<Court, Long> {
}
