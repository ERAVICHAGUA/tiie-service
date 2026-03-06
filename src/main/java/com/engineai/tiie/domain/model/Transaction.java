package com.engineai.tiie.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, length = 20)
    private String source;

    @Column(nullable = false, length = 20)
    private String type;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, length = 10)
    private String currency;

    @Column(name = "raw_description", nullable = false)
    private String rawDescription;

    @Column(name = "normalized_description")
    private String normalizedDescription;

    @Column(name = "merchant_raw")
    private String merchantRaw;

    @Column(name = "merchant_normalized")
    private String merchantNormalized;

    @Column
    private String category;

    @Column
    private String subcategory;

    @Column(precision = 5, scale = 2)
    private BigDecimal confidence;

    @Column(name = "classification_method")
    private String classificationMethod;

    @Column(nullable = false)
    private String status;

    @Column(name = "occurred_at", nullable = false)
    private LocalDateTime occurredAt;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;
}