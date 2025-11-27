package com.example.lendingplatform.repository;

import com.example.lendingplatform.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByBorrowerId(Long borrowerId);
    List<Request> findByItemId(Long itemId);
}