package com.vedantu.ecommerce.service.impl;

import com.vedantu.ecommerce.model.Inventory;
import com.vedantu.ecommerce.model.ItemsInfo;
import com.vedantu.ecommerce.model.OrderResponse;
import com.vedantu.ecommerce.model.ResponseMessage;
import com.vedantu.ecommerce.repository.InventoryRepository;
import com.vedantu.ecommerce.service.InventoryService;
import com.vedantu.ecommerce.service.SequenceGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private static Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    SequenceGenerator sequenceGenerator;

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public Boolean addInventory(Inventory inventory) {

        Inventory existingInventory = inventoryRepository.findByProductId(inventory.getProductId());
        if (existingInventory != null){
            existingInventory.setQuantity(existingInventory.getQuantity() + inventory.getQuantity());
        }else {
            existingInventory = new Inventory();
            existingInventory.setInventoryId(sequenceGenerator.generateSequence(Inventory.SEQUENCE_NAME));
            existingInventory.setProductId(inventory.getProductId());
            existingInventory.setProductName(inventory.getProductName());
            existingInventory.setQuantity(inventory.getQuantity());
            logger.info("Inventory newly added : {}", existingInventory);
        }
        try{
            if (inventoryRepository.save(existingInventory) != null){
                logger.info("Inventory saved : {}", inventory);
                return true;
            }
        }catch (Exception e){
            logger.error("Error while saving in inventory");
            return false;
        }
        logger.info("Inventory not saved : {}", inventory);
        return false;
    }

    @Override
    public OrderResponse assignInventory(List<ItemsInfo> itemsInfoList) {

        OrderResponse orderResponse = new OrderResponse();
        List<ItemsInfo> inStockItems = new ArrayList<>();
        for (ItemsInfo itemsInfo: itemsInfoList) {
            if (itemsInfo.getProductId() != null){

                Inventory inventory = inventoryRepository.findByProductId(itemsInfo.getProductId());
                if (inventory.getQuantity() < itemsInfo.getOrderQty()){
                    logger.info("INSUFFICIENT_INVENTORY : inventory {} and orderQty", inventory, itemsInfo.getOrderQty());
                    orderResponse.setResponseString(ResponseMessage.INSUFFICIENT_INVENTORY);
                    return orderResponse;
                }
                reduceInventory(inventory, itemsInfo.getOrderQty());
            }
            logger.info("In stock items : {}", itemsInfo);
            inStockItems.add(itemsInfo);
        }
        orderResponse.setItemsInfos(inStockItems);
        orderResponse.setResponseString(ResponseMessage.SUFFICIENT_INVENTORY);
        return orderResponse;
    }

    private synchronized void reduceInventory(Inventory inventory, int orderQty){

        inventory.setQuantity(inventory.getQuantity() - orderQty);
        inventoryRepository.save(inventory);
        logger.info("Inventory reduced for order : {}", inventory);
    }
}
