package com.example.lendingplatform.service;

import com.example.lendingplatform.model.Item;
import com.example.lendingplatform.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item create(Item item) {
        item.setStatus("Available");
        return itemRepository.save(item);
    }

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public Item getById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    public Item update(Long id, Item updateItem) {
        return itemRepository.findById(id).map(item -> {
            item.setTitle(updateItem.getTitle());
            item.setDescription(updateItem.getDescription());
            item.setPricePerDay(updateItem.getPricePerDay());
            item.setSecurityDeposit(updateItem.getSecurityDeposit());
            item.setPickupLocation(updateItem.getPickupLocation());
            return itemRepository.save(item);
        }).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    public Item updateStatus(Long id, String status) {
        return itemRepository.findById(id).map(item -> {
            item.setStatus(status);
            return itemRepository.save(item);
        }).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }
}