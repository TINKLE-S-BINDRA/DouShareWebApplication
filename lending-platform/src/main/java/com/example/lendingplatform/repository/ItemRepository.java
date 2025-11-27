package com.example.lendingplatform.repository;

import com.example.lendingplatform.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByStatus(String status);
}