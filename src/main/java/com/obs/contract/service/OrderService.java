package com.obs.contract.service;

import com.obs.entity.Order;
import com.obs.model.OrderRequest;

import java.math.BigInteger;
import java.util.List;

public interface OrderService {
    List<Order> getAllOrder(int pageNo, int pageSize);
    List<Order> getOrderByOrderId(String orderId, int pageNo, int pageSize);
    List<Order> getOrderByItemId(BigInteger itemId, int pageNo, int pageSize);
    String orderAdd(List<OrderRequest> payload);
    String orderUpdate(String orderId, List<OrderRequest> payload);
    String orderDelete(String orderId);
}
