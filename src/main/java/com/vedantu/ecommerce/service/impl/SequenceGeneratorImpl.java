package com.vedantu.ecommerce.service.impl;

import com.vedantu.ecommerce.model.DatabaseSequence;
import com.vedantu.ecommerce.service.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SequenceGeneratorImpl implements SequenceGenerator {

    @Autowired
    MongoOperations mongoOperations;

    public int generateSequence(String seqName) {

        DatabaseSequence counter = mongoOperations.findAndModify(Query.query(Criteria.where("_id").is(seqName)),
                new Update().inc("seq",1), FindAndModifyOptions.options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? (int) counter.getSeq() : 1;
    }
}
