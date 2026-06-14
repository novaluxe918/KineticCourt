package com.hoainhi.sportfields.repository;


import com.hoainhi.sportfields.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  AccountRepository extends JpaRepository<User, Long> {
}
