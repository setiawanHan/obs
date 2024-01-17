package com.obs.service;

import com.obs.contract.service.ItemService;
import com.obs.contract.service.OrderService;
import com.obs.entity.Item;
import com.obs.entity.Order;
import com.obs.model.OrderRequest;
import com.obs.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemService itemService;

    @Override
    public List<Order> getAllOrder(int pageNo, int pageSize) {
        Page<Order> orderResult = orderRepository.findAll(PageRequest.of(pageNo, pageSize));
        if (orderResult.hasContent())
            return orderResult.getContent();
        return Collections.emptyList();
    }

    @Override
    public List<Order> getOrderByOrderId(String orderId, int pageNo, int pageSize) {
        List<Order> listOrderByOrderId = orderRepository.findByOrderId(orderId, PageRequest.of(pageNo, pageSize));
        if (listOrderByOrderId.size() == 0)
            return Collections.emptyList();
        return listOrderByOrderId;
    }

    @Override
    public List<Order> getOrderByItemId(BigInteger itemId, int pageNo, int pageSize) {
        List<Order> listOrderByItemId = orderRepository.findByItem_Id(itemId, PageRequest.of(pageNo, pageSize));
        if (listOrderByItemId.size() == 0)
            return Collections.emptyList();
        return listOrderByItemId;
    }

    @Transactional
    @Override
    public String orderAdd(List<OrderRequest> payload) {
        if (payload.size() == 0)
            throw new RuntimeException("no order find in this request.");

        List<Order> finalOrder = new ArrayList<>();
        for (OrderRequest order : payload) {
            Order o = Order.builder()
                        .orderId("O" + String.valueOf(orderRepository.findDistinctOrderId().size() + 1))
                        .item(itemService.getItemById(order.getItemId()))
                        .qty(order.getQty())
                        .build();
            finalOrder.add(o);
        }
        orderRepository.saveAll(finalOrder);

        return "Success";
    }

    @Transactional
    @Override
    public String orderUpdate(String orderId, List<OrderRequest> payload) {
        if (payload.size() == 0)
            throw new RuntimeException("no order can be processed.");

        List<Order> order = orderRepository.findByOrderIdWithoutPageable(orderId);
        for (OrderRequest o1 : payload) {
            for (Order o2 : order) {
                if (!o2.getItem().getId().equals(o1.getItemId()))
                    continue;
                o2.setQty(o1.getQty());
            }
        }
        orderRepository.saveAll(order);

        return "Success";
    }

    @Transactional
    @Override
    public String orderDelete(String orderId) {
        orderRepository.deleteByOrderId(orderId);
        return "Success";
    }
}
