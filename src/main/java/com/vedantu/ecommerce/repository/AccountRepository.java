package com.vedantu.ecommerce.repository;

import com.vedantu.ecommerce.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    public Account findByAccountId(Integer accountId);
}
