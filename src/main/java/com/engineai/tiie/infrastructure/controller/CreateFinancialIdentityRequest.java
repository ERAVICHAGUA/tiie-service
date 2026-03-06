package com.engineai.fice.infrastructure.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFinancialIdentityRequest {

    private Long userId;
    private String incomeType;
    private int incomeStabilityScore;
    private String riskTolerance;
    private String decisionStyle;
}
