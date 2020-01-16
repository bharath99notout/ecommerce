package com.vedantu.ecommerce.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "Accounts")
public class Account {

    @Transient
    public static final String SEQUENCE_NAME = "account";

    private String id;

    @Indexed(background = true, unique = true)
    private int accountId;

    private String userName;

    private Date createdDate;
}
