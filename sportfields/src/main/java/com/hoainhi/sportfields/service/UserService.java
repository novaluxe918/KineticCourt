package com.hoainhi.sportfields.service;

import com.hoainhi.sportfields.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    Page<User> getAllUsers(Pageable pageable);
    void toggleUserStatus(Long id);
}
