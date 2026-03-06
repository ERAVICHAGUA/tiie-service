package com.engineai.tiie.application.service;

import com.engineai.tiie.domain.model.Transaction;
import com.engineai.tiie.domain.repository.TransactionRepository;
import com.engineai.tiie.infrastructure.controller.CreateTransactionRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction create(CreateTransactionRequest request) {
        String normalizedDescription = normalizeText(request.rawDescription());
        String merchantNormalized = normalizeText(request.merchantRaw());

        String category = classifyCategory(normalizedDescription, merchantNormalized);
        BigDecimal confidence = resolveConfidence(category);

        Transaction transaction = Transaction.builder()
                .userId(request.userId())
                .source("manual")
                .type(request.type())
                .amount(request.amount())
                .currency(request.currency() == null || request.currency().isBlank() ? "PEN" : request.currency())
                .rawDescription(request.rawDescription())
                .normalizedDescription(normalizedDescription)
                .merchantRaw(request.merchantRaw())
                .merchantNormalized(merchantNormalized)
                .category(category)
                .subcategory(null)
                .confidence(confidence)
                .classificationMethod("rules")
                .status("CLASSIFIED")
                .occurredAt(LocalDateTime.parse(request.occurredAt()))
                .build();

        return transactionRepository.save(transaction);
    }

    public List<Transaction> findByUserId(Long userId) {
        return transactionRepository.findByUserIdOrderByOccurredAtDesc(userId);
    }

    private String normalizeText(String value) {
        if (value == null) return null;
        return value.trim().toLowerCase(Locale.ROOT).replaceAll("\\s+", " ");
    }

    private String classifyCategory(String description, String merchant) {
        String text = (description == null ? "" : description) + " " + (merchant == null ? "" : merchant);

        if (text.contains("bodega") || text.contains("tottus") || text.contains("plaza vea") || text.contains("metro")) {
            return "groceries";
        }
        if (text.contains("uber") || text.contains("cabify") || text.contains("grifo") || text.contains("primax")) {
            return "transport";
        }
        if (text.contains("netflix") || text.contains("spotify") || text.contains("cine")) {
            return "entertainment";
        }
        if (text.contains("salary") || text.contains("freelance") || text.contains("payment")) {
            return "income";
        }

        return "others";
    }

    private BigDecimal resolveConfidence(String category) {
        return "others".equals(category) ? new BigDecimal("0.50") : new BigDecimal("0.90");
    }
}