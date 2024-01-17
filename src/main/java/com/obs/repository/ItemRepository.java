package com.obs.repository;

import com.obs.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, BigInteger> {
    Optional<Item> findById(BigInteger itemId);
    Optional<Item> findByName(String itemName);
}
