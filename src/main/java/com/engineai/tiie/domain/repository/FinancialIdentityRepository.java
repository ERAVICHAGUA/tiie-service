package com.engineai.fice.domain.repository;

import com.engineai.fice.domain.model.FinancialIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FinancialIdentityRepository
        extends JpaRepository<FinancialIdentity, Long> {

    Optional<FinancialIdentity> findByUserId(Long userId);
}
