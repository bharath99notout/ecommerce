package com.vedantu.ecommerce.repository;

import com.vedantu.ecommerce.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

    public Product findByProductId(int productId);
}
