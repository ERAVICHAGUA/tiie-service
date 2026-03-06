package com.engineai.fice.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "financial_identity")
@Getter
@Setter
public class FinancialIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "income_type", nullable = false, length = 50)
    private String incomeType;

    @Column(name = "income_stability_score", nullable = false)
    private Integer incomeStabilityScore;

    @Column(name = "risk_tolerance", nullable = false, length = 20)
    private String riskTolerance;

    @Column(name = "decision_style", nullable = false, length = 30)
    private String decisionStyle;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;
}
