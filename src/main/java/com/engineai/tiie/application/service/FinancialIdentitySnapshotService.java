package com.engineai.fice.application.service;

import com.engineai.fice.domain.model.FinancialIdentity;
import com.engineai.fice.domain.model.FinancialIdentitySnapshot;
import com.engineai.fice.domain.repository.FinancialIdentitySnapshotRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FinancialIdentitySnapshotService {

    private final FinancialIdentitySnapshotRepository snapshotRepository;

    private final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    public FinancialIdentitySnapshotService(FinancialIdentitySnapshotRepository snapshotRepository) {
        this.snapshotRepository = snapshotRepository;
    }

    public void createSnapshot(FinancialIdentity identity, String changeReason) {
        try {
            FinancialIdentitySnapshot snapshot = new FinancialIdentitySnapshot();

            snapshot.setFinancialIdentityId(identity.getId());
            snapshot.setSnapshotData(objectMapper.writeValueAsString(identity));
            snapshot.setChangeReason(changeReason);
            snapshot.setCreatedAt(LocalDateTime.now());

            snapshotRepository.save(snapshot);

        } catch (Exception e) {
            throw new RuntimeException("Error creating financial identity snapshot");
        }
    }

    public List<FinancialIdentitySnapshot> getSnapshotsByFinancialIdentityId(Long financialIdentityId) {
        return snapshotRepository.findByFinancialIdentityIdOrderByCreatedAtDesc(financialIdentityId);
    }
}
