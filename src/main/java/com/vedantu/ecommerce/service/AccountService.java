package com.vedantu.ecommerce.service;

import com.vedantu.ecommerce.model.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    public Account createAccount(Account account);

    public Account verifyUser(Integer userId);
}
