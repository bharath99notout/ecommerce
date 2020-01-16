package com.vedantu.ecommerce.model;

import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

@Data
public class Product {

    @Transient
    public static final String SEQUENCE_NAME = "product";

    private String id;

    @Indexed(background = true, unique = true)
    private int productId;

    private String productName;

    private Date createDate;
}
