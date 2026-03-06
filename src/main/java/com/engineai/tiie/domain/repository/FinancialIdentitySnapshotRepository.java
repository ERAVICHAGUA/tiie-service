package com.engineai.fice.domain.repository;

import com.engineai.fice.domain.model.FinancialIdentitySnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialIdentitySnapshotRepository
        extends JpaRepository<FinancialIdentitySnapshot, Long> {

    List<FinancialIdentitySnapshot> findByFinancialIdentityIdOrderByCreatedAtDesc(Long financialIdentityId);
}
