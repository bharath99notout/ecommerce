package com.vedantu.ecommerce.controller;

import com.vedantu.ecommerce.model.Account;
import com.vedantu.ecommerce.model.Order;
import com.vedantu.ecommerce.model.OrderResponse;
import com.vedantu.ecommerce.model.ResponseMessage;
import com.vedantu.ecommerce.service.AccountService;
import com.vedantu.ecommerce.service.OrderService;
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
@RequestMapping("api/v1/ecom/order")
public class OrderController {

    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    AccountService accountService;

    @Autowired
    OrderService orderService;


    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public ResponseEntity<OrderResponse> createOrder(@RequestBody Order order){

        OrderResponse orderResponse = new OrderResponse();
        if (order == null || order.getItemsInfo() == null || order.getUserId() == null){
            logger.info("Order is empty or Order item info is empty");
            orderResponse.setResponseString(ResponseMessage.ORDER_IS_EMPTY);
            return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.BAD_REQUEST);
        }
        Account account = accountService.verifyUser(order.getUserId());
        if (account == null){
            logger.info("user is not exist in database");
            orderResponse.setResponseString(ResponseMessage.USER_ID_IS_INVALID);
            return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<OrderResponse>(orderService.createOrder(order), HttpStatus.OK);
    }
}
