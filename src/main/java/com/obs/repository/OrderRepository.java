package com.obs.repository;

import com.obs.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, BigInteger> {
    List<Order> findByOrderId(String orderId, Pageable pageable);
    @Query(nativeQuery = true, value = "SELECT * FROM ORDERTBL WHERE ORDER_ID = ?1")
    List<Order> findByOrderIdWithoutPageable(String orderId);
    List<Order> findByItem_Id(BigInteger itemId, Pageable pageable);
    @Query(nativeQuery = true, value = "SELECT DISTINCT ORDER_ID FROM ORDERTBL")
    List<String> findDistinctOrderId();
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM ORDERTBL WHERE ORDER_ID = ?1")
    void deleteByOrderId(String orderId);
}
