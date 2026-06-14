package com.hoainhi.sportfields.service;

import com.hoainhi.sportfields.dto.AccountDTO;
import com.hoainhi.sportfields.entity.User;

public interface AccountService {
    public User registerUser(AccountDTO accountDTO);

}
