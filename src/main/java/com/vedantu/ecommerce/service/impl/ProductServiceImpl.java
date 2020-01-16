package com.vedantu.ecommerce.service.impl;

import com.vedantu.ecommerce.model.Product;
import com.vedantu.ecommerce.repository.ProductRepository;
import com.vedantu.ecommerce.service.ProductService;
import com.vedantu.ecommerce.service.SequenceGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    SequenceGenerator sequenceGenerator;

    @Autowired
    ProductRepository productRepository;


    @Override
    public Boolean addProduct(Product product) {

        //To do check for product name exists in db
        // if exists in dont add the product
        product.setProductId(sequenceGenerator.generateSequence(Product.SEQUENCE_NAME));
        product.setCreateDate(new Date());
        if (productRepository.save(product) != null){
            logger.info("Product saved : {}", product);
            return true;
        }
        logger.info("Product not saved : {}", product);
        return false;
    }

    @Override
    public Product verifyProductExist(int productId) {

        Product product = productRepository.findByProductId(productId);
        if (product != null){
            logger.info("Product exist : {}", product);
            return product;
        }
        return product;
    }
}
