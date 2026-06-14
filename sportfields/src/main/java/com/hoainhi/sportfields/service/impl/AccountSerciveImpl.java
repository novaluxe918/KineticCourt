package com.hoainhi.sportfields.service.impl;

import com.hoainhi.sportfields.dto.AccountDTO;
import com.hoainhi.sportfields.entity.User;
import com.hoainhi.sportfields.enums.Role;
import com.hoainhi.sportfields.repository.AccountRepository;
import com.hoainhi.sportfields.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountSerciveImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public User registerUser(AccountDTO accountDTO) {

        User user = new User();
        BeanUtils.copyProperties(accountDTO, user);
        user.setRole(Role.User);
        accountRepository.save(user);
        return user;
    }
}
