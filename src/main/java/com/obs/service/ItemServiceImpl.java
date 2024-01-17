package com.obs.service;

import com.obs.entity.Item;
import com.obs.model.ItemRequest;
import com.obs.repository.ItemRepository;
import com.obs.contract.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getAllItem(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Item> itemResult = itemRepository.findAll(paging);
        if (itemResult.hasContent())
            return itemResult.getContent();
        return Collections.emptyList();
    }

    @Override
    public Item getItemById(BigInteger itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        return item.orElse(null);
    }

    @Override
    public Item getItemByName(String itemName) {
        Optional<Item> item = itemRepository.findByName(itemName);
        return item.orElse(null);
    }

    @Override
    public String addNewItem(ItemRequest payload) {
        Item itemToSave = Item.builder()
                .name(payload.getItemName())
                .price(payload.getItemPrice())
                .build();
        itemRepository.save(itemToSave);
        return "Success";
    }

    @Override
    public String editItem(BigInteger itemId, ItemRequest payload) {
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isEmpty())
            throw new RuntimeException("no item found with id " + itemId);

        item.get().setName(payload.getItemName());
        item.get().setPrice(payload.getItemPrice());

        itemRepository.save(item.get());
        return "Success";
    }

    @Override
    public String deleteItem(BigInteger itemId) {
        itemRepository.deleteById(itemId);
        return "Success";
    }
}
