package com.obs.contract.api;

import com.obs.entity.Order;
import com.obs.model.OrderRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.List;

@Tag(name = "ORDER", description = "OrderApi.class")
@RequestMapping("/order")
public interface OrderApi {
    @GetMapping("/getAll")
    ResponseEntity<List<Order>> getAllItem(@RequestParam(defaultValue = "0") BigInteger pageNo,
                                           @RequestParam(defaultValue = "10") BigInteger pageSize);
    @GetMapping("/getOrderByOrderId")
    ResponseEntity<List<Order>> getOrderByOrderId(@RequestParam String orderId,
                                                  @RequestParam(defaultValue = "0") BigInteger pageNo,
                                                  @RequestParam(defaultValue = "10") BigInteger pageSize);
    @GetMapping("/getOrderByItemId")
    ResponseEntity<List<Order>> getOrderByItemId(@RequestParam BigInteger itemId,
                                                 @RequestParam(defaultValue = "0") BigInteger pageNo,
                                                 @RequestParam(defaultValue = "10") BigInteger pageSize);
    @PostMapping("/orderAdd")
    ResponseEntity<String> orderAdd(@RequestBody List<OrderRequest> payload);
    @PutMapping("/orderQtyUpdate")
    ResponseEntity<String> orderQtyUpdate(@RequestParam String orderId,
                                          @RequestBody List<OrderRequest> payload);
    @DeleteMapping("/orderDelete")
    ResponseEntity<String> orderDelete(@RequestParam String orderId);
}
