package com.obs.controller;

import com.obs.contract.api.ItemApi;
import com.obs.contract.service.ItemService;
import com.obs.entity.Item;
import com.obs.model.ItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
public class ItemController implements ItemApi {
    @Autowired
    private ItemService itemService;

    @Override
    public ResponseEntity<List<Item>> getAllItem(BigInteger pageNo, BigInteger pageSize) {
        List<Item> listItem = itemService.getAllItem(
                Integer.parseInt(pageNo.toString()), Integer.parseInt(pageSize.toString()));
        return new ResponseEntity<>(listItem, new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Item> getItemById(BigInteger itemId) {
        return new ResponseEntity<>(itemService.getItemById(itemId), new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Item> getItemByName(String itemName) {
        return new ResponseEntity<>(itemService.getItemByName(itemName), new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> addNewItem(ItemRequest payload) {
        return new ResponseEntity<>(itemService.addNewItem(payload), new HttpHeaders(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> updateItem(BigInteger itemId, ItemRequest payload) {
        return new ResponseEntity<>(itemService.editItem(itemId, payload), new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteItem(BigInteger itemId) {
        return new ResponseEntity<>(itemService.deleteItem(itemId), new HttpHeaders(), HttpStatus.OK);
    }
}
