package com.hoainhi.sportfields.service.impl;

import com.hoainhi.sportfields.entity.User;
import com.hoainhi.sportfields.repository.UserRepository;
import com.hoainhi.sportfields.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
