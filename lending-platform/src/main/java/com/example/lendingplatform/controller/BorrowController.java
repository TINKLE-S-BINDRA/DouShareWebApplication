package com.example.lendingplatform.controller;

import com.example.lendingplatform.model.Borrow;
import com.example.lendingplatform.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {

    @Autowired
    private BorrowRepository borrowRepository;

    // Create a borrow record
    @PostMapping
    public Borrow createBorrow(@RequestBody Borrow borrow) {
        borrow.setStatus("Active");
        borrow.setBorrowDate(LocalDate.now());
        return borrowRepository.save(borrow);
    }

    // Get all borrows
    @GetMapping
    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    // Get borrow by ID
    @GetMapping("/{id}")
    public ResponseEntity<Borrow> getBorrow(@PathVariable Long id) {
        return borrowRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Mark item as returned
    @PatchMapping("/{id}/return")
    public ResponseEntity<Borrow> markReturned(@PathVariable Long id) {
        return borrowRepository.findById(id).map(borrow -> {
            borrow.setActualReturnDate(LocalDate.now());
            borrow.setStatus("Returned");
            return ResponseEntity.ok(borrowRepository.save(borrow));
        }).orElse(ResponseEntity.notFound().build());
    }
}