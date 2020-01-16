package com.vedantu.ecommerce.service.impl;

import com.vedantu.ecommerce.model.Account;
import com.vedantu.ecommerce.repository.AccountRepository;
import com.vedantu.ecommerce.service.AccountService;
import com.vedantu.ecommerce.service.SequenceGenerator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService{

    private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    SequenceGenerator sequenceGenerator;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {

        account.setAccountId(sequenceGenerator.generateSequence(Account.SEQUENCE_NAME));
        account.setCreatedDate(new Date());
        if (accountRepository.save(account) != null){
            logger.info("account saved : {}", account);
            return account;
        }
        logger.info("account not saved : {}", account);
        return account;
    }

    @Override
    public Account verifyUser(Integer userId) {

        Account account = accountRepository.findByAccountId(userId);
        if (account != null){
            logger.info("valid  user : {}", account);
            return account;
        }
        logger.info("user not exist in database : {}", account);
        return account;
    }
}
