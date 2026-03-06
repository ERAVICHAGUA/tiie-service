package com.engineai.fice.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "financial_identity_snapshot")
public class FinancialIdentitySnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "financial_identity_id", nullable = false)
    private Long financialIdentityId;

    @Column(name = "snapshot_data", nullable = false)
    private String snapshotData;

    @Column(name = "change_reason")
    private String changeReason;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
