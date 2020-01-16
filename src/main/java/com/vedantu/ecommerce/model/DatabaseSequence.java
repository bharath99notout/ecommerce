package com.vedantu.ecommerce.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "databaseSequences")
@Data
public class DatabaseSequence {

    @Id
    private String id;

    private long seq;
}
