package com.engineai.fice.application.service;

import com.engineai.fice.domain.model.FinancialIdentity;
import com.engineai.fice.domain.repository.FinancialIdentityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FinancialIdentityService {

    private final FinancialIdentityRepository repository;
    private final FinancialIdentitySnapshotService snapshotService;

    public FinancialIdentityService(
            FinancialIdentityRepository repository,
            FinancialIdentitySnapshotService snapshotService
    ) {
        this.repository = repository;
        this.snapshotService = snapshotService;
    }

    public FinancialIdentity create(
            Long userId,
            String incomeType,
            int incomeStabilityScore,
            String riskTolerance,
            String decisionStyle
    ) {

        FinancialIdentity identity = new FinancialIdentity();
        identity.setUserId(userId);
        identity.setIncomeType(incomeType);
        identity.setIncomeStabilityScore(incomeStabilityScore);
        identity.setRiskTolerance(riskTolerance);
        identity.setDecisionStyle(decisionStyle);
        identity.setCreatedAt(LocalDateTime.now());
        identity.setLastUpdated(LocalDateTime.now());

        FinancialIdentity saved = repository.save(identity);

        snapshotService.createSnapshot(saved, "CREATED");

        return saved;
    }

    public FinancialIdentity getByUserId(Long userId) {
        return repository.findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException("Financial identity not found for user"));
    }

    public FinancialIdentity update(
            Long id,
            String incomeType,
            int incomeStabilityScore,
            String riskTolerance,
            String decisionStyle
    ) {

        FinancialIdentity existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Financial identity not found"));

        existing.setIncomeType(incomeType);
        existing.setIncomeStabilityScore(incomeStabilityScore);
        existing.setRiskTolerance(riskTolerance);
        existing.setDecisionStyle(decisionStyle);
        existing.setLastUpdated(LocalDateTime.now());

        FinancialIdentity updated = repository.save(existing);

        snapshotService.createSnapshot(updated, "UPDATED");

        return updated;
    }
}
