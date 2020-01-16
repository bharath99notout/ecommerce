package com.vedantu.ecommerce.model;

import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Order {

    @Transient
    public static final String SEQUENCE_NAME = "order";

    private String id;

    private String orderId;

    private Integer userId;

    private String userName;

    private List<ItemsInfo> itemsInfo;
}
