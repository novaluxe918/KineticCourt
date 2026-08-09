package com.hoainhi.sportfields.service;

import com.hoainhi.sportfields.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();
}
