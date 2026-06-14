package com.hoainhi.sportfields.repository;


import com.hoainhi.sportfields.entity.User;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  AccountRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(@NotEmpty(message = "Email cannot be empty") String email);
}
