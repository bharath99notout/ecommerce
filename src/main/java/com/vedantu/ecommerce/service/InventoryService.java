package com.vedantu.ecommerce.service;

import com.vedantu.ecommerce.model.Inventory;
import com.vedantu.ecommerce.model.ItemsInfo;
import com.vedantu.ecommerce.model.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventoryService {

    public Boolean addInventory(Inventory inventory);

    public OrderResponse assignInventory(List<ItemsInfo> itemsInfos);
}
