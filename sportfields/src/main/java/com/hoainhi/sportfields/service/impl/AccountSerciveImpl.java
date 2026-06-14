package com.hoainhi.sportfields.service.impl;

import com.hoainhi.sportfields.dto.AccountDTO;
import com.hoainhi.sportfields.entity.User;
import com.hoainhi.sportfields.enums.Role;
import com.hoainhi.sportfields.repository.AccountRepository;
import com.hoainhi.sportfields.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AccountSerciveImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User registerUser(AccountDTO accountDTO) {
        accountDTO.setPassword(new BCryptPasswordEncoder().encode(accountDTO.getPassword()));
        User user = new User();
        BeanUtils.copyProperties(accountDTO, user);
        user.setRole(Role.User);
        accountRepository.save(user);
        return user;
    }

    @Override
    public boolean existsByEmail(String email) {
         return accountRepository.existsByEmail(email);
    }

    @Override
    public User loginUser(AccountDTO accountDTO) {
        Optional<User> optionalUser = accountRepository.findByEmail(accountDTO.getEmail());
        if(optionalUser.isPresent() && bCryptPasswordEncoder.matches(accountDTO.getPassword(), optionalUser.get().getPassword())){
            return optionalUser.get();
        }

        return null;
    }


}
