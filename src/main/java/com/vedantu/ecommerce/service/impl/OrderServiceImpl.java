package com.vedantu.ecommerce.service.impl;

import com.vedantu.ecommerce.model.ItemsInfo;
import com.vedantu.ecommerce.model.Order;
import com.vedantu.ecommerce.model.OrderResponse;
import com.vedantu.ecommerce.model.ResponseMessage;
import com.vedantu.ecommerce.repository.InventoryRepository;
import com.vedantu.ecommerce.repository.OrderRepository;
import com.vedantu.ecommerce.service.InventoryService;
import com.vedantu.ecommerce.service.OrderService;
import com.vedantu.ecommerce.service.SequenceGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    InventoryService inventoryService;

    @Autowired
    SequenceGenerator sequenceGenerator;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public OrderResponse createOrder(Order order) {
        logger.info("Inisde create Order");
        List<ItemsInfo> itemsInfoList = order.getItemsInfo();
        OrderResponse orderResponse = inventoryService.assignInventory(order.getItemsInfo());
        if (orderResponse.getResponseString().equals(ResponseMessage.SUFFICIENT_INVENTORY)){
            if (createAndSaveOrder(order)){
                orderResponse.setResponseString(ResponseMessage.ORDER_CREATED);
                return orderResponse;
            }
        }
        return orderResponse;
    }

    public boolean createAndSaveOrder(Order order){
        order.setOrderId("ORDER"+sequenceGenerator.generateSequence(Order.SEQUENCE_NAME));
        if (orderRepository.save(order) != null){
            logger.info("Order is placed");
            return true;
        }
        logger.info("Order not placed");
        return false;
    }
}
