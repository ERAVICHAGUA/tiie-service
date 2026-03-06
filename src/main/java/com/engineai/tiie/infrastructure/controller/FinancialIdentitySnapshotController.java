package com.engineai.fice.infrastructure.controller;

import com.engineai.fice.application.service.FinancialIdentitySnapshotService;
import com.engineai.fice.domain.model.FinancialIdentitySnapshot;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/financial-identity-snapshots")
public class FinancialIdentitySnapshotController {

    private final FinancialIdentitySnapshotService snapshotService;

    public FinancialIdentitySnapshotController(FinancialIdentitySnapshotService snapshotService) {
        this.snapshotService = snapshotService;
    }

    @GetMapping("/{financialIdentityId}")
    public List<FinancialIdentitySnapshot> getSnapshots(@PathVariable Long financialIdentityId) {
        return snapshotService.getSnapshotsByFinancialIdentityId(financialIdentityId);
    }
}
