package com.example.lendingplatform.service;

import com.example.lendingplatform.model.Borrow;
import com.example.lendingplatform.repository.BorrowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository borrowRepository;

    public Borrow create(Borrow borrow) {
        borrow.setStatus("Active");
        borrow.setBorrowDate(LocalDate.now());
        return borrowRepository.save(borrow);
    }

    public List<Borrow> getAll() {
        return borrowRepository.findAll();
    }

    public Borrow getById(Long id) {
        return borrowRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));
    }

    public Borrow markReturned(Long id) {
        return borrowRepository.findById(id).map(bor -> {
            bor.setActualReturnDate(LocalDate.now());
            bor.setStatus("Returned");
            return borrowRepository.save(bor);
        }).orElseThrow(() -> new RuntimeException("Borrow not found"));
    }
}