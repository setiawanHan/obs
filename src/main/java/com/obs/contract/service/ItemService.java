package com.obs.contract.service;

import com.obs.entity.Item;
import com.obs.model.ItemRequest;

import java.math.BigInteger;
import java.util.List;

public interface ItemService {
    List<Item> getAllItem(int pageNo, int pageSize);
    Item getItemById(BigInteger itemId);
    Item getItemByName(String itemName);
    String addNewItem(ItemRequest payload);
    String editItem(BigInteger itemId, ItemRequest payload);
    String deleteItem(BigInteger itemId);
}
