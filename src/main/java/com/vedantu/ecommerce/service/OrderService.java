package com.vedantu.ecommerce.service;

import com.vedantu.ecommerce.model.Order;
import com.vedantu.ecommerce.model.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    public OrderResponse createOrder(Order order);
}
