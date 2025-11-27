package com.example.lendingplatform.repository;

import com.example.lendingplatform.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {

}