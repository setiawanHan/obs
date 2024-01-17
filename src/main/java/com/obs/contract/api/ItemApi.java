package com.obs.contract.api;

import com.obs.entity.Item;
import com.obs.model.ItemRequest;
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

@Tag(name = "ITEM", description = "ItemApi.class")
@RequestMapping("/item")
public interface ItemApi {
    @GetMapping("/getAll")
    ResponseEntity<List<Item>> getAllItem(@RequestParam(defaultValue = "0") BigInteger pageNo,
                                          @RequestParam(defaultValue = "10") BigInteger pageSize);
    @GetMapping("/getItemById")
    ResponseEntity<Item> getItemById(@RequestParam BigInteger itemId);
    @GetMapping("/getItemByName")
    ResponseEntity<Item> getItemByName(@RequestParam String itemName);
    @PostMapping("/addNewItem")
    ResponseEntity<String> addNewItem(@RequestBody ItemRequest payload);
    @PutMapping("/updateItem")
    ResponseEntity<String> updateItem(@RequestParam BigInteger itemId,
                                      @RequestBody ItemRequest payload);
    @DeleteMapping("/deleteItem")
    ResponseEntity<String> deleteItem(@RequestParam BigInteger itemId);
}
