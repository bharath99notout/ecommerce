package com.vedantu.ecommerce.controller;

import com.vedantu.ecommerce.model.Inventory;
import com.vedantu.ecommerce.model.Product;
import com.vedantu.ecommerce.service.InventoryService;
import com.vedantu.ecommerce.service.ProductService;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping("api/v1/ecom/inventory")
public class InventoryController {

    private static Logger logger = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    InventoryService inventoryService;

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/addInventory" , method = RequestMethod.POST)
    public ResponseEntity<Boolean> addInventory(@RequestBody Inventory inventory){

        if (StringUtils.isEmpty(inventory.getProductId()) || inventory.getQuantity() <= 0){
            logger.info("productId is empty : {} or Qty is Zero : {}", inventory);
            return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
        }
        Product product = productService.verifyProductExist(inventory.getProductId());
        if (product == null){
            logger.info("productId does not exist", inventory.getProductId());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        inventory.setProductName(product.getProductName());
        return new ResponseEntity<Boolean>(inventoryService.addInventory(inventory),HttpStatus.OK);
    }

}
