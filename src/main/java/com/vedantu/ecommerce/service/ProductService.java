package com.vedantu.ecommerce.service;

import com.vedantu.ecommerce.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    public Boolean addProduct(Product product);

    public Product verifyProductExist(int productId);
}
