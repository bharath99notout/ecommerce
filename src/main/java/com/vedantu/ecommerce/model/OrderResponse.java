package com.vedantu.ecommerce.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {

    private ResponseMessage responseString;

    private List<ItemsInfo> itemsInfos;

}
