package com.example.lendingplatform.controller;

import com.example.lendingplatform.model.Item;
import com.example.lendingplatform.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    // Create item
    @PostMapping
    public Item createItem(@RequestBody Item item) {
        item.setStatus("Available");
        return itemRepository.save(item);
    }

    // Get all items
    @GetMapping
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // Get item by ID
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id) {
        return itemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update item
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item itemDetails) {
        return itemRepository.findById(id).map(item -> {
            item.setTitle(itemDetails.getTitle());
            item.setDescription(itemDetails.getDescription());
            item.setPricePerDay(itemDetails.getPricePerDay());
            item.setSecurityDeposit(itemDetails.getSecurityDeposit());
            item.setPickupLocation(itemDetails.getPickupLocation());
            // Keep status as is or update accordingly
            return ResponseEntity.ok(itemRepository.save(item));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Change status
    @PatchMapping("/{id}/status")
    public ResponseEntity<Item> updateStatus(@PathVariable Long id, @RequestBody String status) {
        return itemRepository.findById(id).map(item -> {
            item.setStatus(status);
            return ResponseEntity.ok(itemRepository.save(item));
        }).orElse(ResponseEntity.notFound().build());
    }
}