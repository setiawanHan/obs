package com.obs.controller;

import com.obs.contract.api.OrderApi;
import com.obs.contract.service.OrderService;
import com.obs.entity.Order;
import com.obs.model.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
public class OrderController implements OrderApi {

    @Autowired
    private OrderService orderService;

    @Override
    public ResponseEntity<List<Order>> getAllItem(BigInteger pageNo, BigInteger pageSize) {
        List<Order> listOrder = orderService.getAllOrder(
                Integer.parseInt(pageNo.toString()), Integer.parseInt(pageSize.toString()));
        return new ResponseEntity<>(listOrder, new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Order>> getOrderByOrderId(String orderId, BigInteger pageNo, BigInteger pageSize) {
        return new ResponseEntity<>(
                orderService.getOrderByOrderId(
                        orderId, Integer.parseInt(pageNo.toString()), Integer.parseInt(pageSize.toString())),
                new HttpHeaders(), HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<List<Order>> getOrderByItemId(BigInteger itemId, BigInteger pageNo, BigInteger pageSize) {
        return new ResponseEntity<>(
                orderService.getOrderByItemId(
                        itemId, Integer.parseInt(pageNo.toString()), Integer.parseInt(pageSize.toString())),
                new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> orderAdd(List<OrderRequest> payload) {
        return new ResponseEntity<>(orderService.orderAdd(payload), new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> orderQtyUpdate(String orderId, List<OrderRequest> payload) {
        return new ResponseEntity<>(orderService.orderUpdate(orderId, payload), new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> orderDelete(String orderId) {
        return new ResponseEntity<>(orderService.orderDelete(orderId), new HttpHeaders(), HttpStatus.OK);
    }
}
