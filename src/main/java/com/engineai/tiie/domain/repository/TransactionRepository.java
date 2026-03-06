package com.engineai.tiie.domain.repository;

import com.engineai.tiie.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUserIdOrderByOccurredAtDesc(Long userId);

}