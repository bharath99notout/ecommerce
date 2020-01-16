package com.vedantu.ecommerce.controller;


import com.vedantu.ecommerce.model.Product;
import com.vedantu.ecommerce.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ecom/product")
public class ProductController {

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/addProduct" , method = RequestMethod.POST)
    public ResponseEntity<Boolean> addProduct(@RequestBody Product product){

        if (StringUtils.isEmpty(product.getProductName())){
            logger.info("productName is empty");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Boolean>(productService.addProduct(product),HttpStatus.OK);
    }
}
