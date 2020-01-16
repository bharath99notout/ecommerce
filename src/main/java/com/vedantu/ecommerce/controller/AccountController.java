package com.vedantu.ecommerce.controller;

import com.vedantu.ecommerce.model.Account;
import com.vedantu.ecommerce.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/ecom/account")
public class AccountController {

    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public ResponseEntity<Account> createAccount(@RequestBody Account account){

        if (StringUtils.isEmpty(account.getUserName())){
            logger.info("user name is empty");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Account>(accountService.createAccount(account),HttpStatus.OK);
    }
}
