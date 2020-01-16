package com.vedantu.ecommerce.model;

import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Inventory {

    @Transient
    public static final String SEQUENCE_NAME = "inventory";

    private String id;

    @Indexed(background = true)
    private int inventoryId;

    private int productId;

    private String productName;

    private int quantity;
}
