package com.engineai.fice.infrastructure.controller;

import lombok.Data;

@Data
public class UpdateFinancialIdentityRequest {
    private String incomeType;
    private int incomeStabilityScore;
    private String riskTolerance;
    private String decisionStyle;
}

