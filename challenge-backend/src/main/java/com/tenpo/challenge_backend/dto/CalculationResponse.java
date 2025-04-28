package com.tenpo.challenge_backend.dto;

import java.math.BigDecimal;

public record CalculationResponse(BigDecimal sum, BigDecimal percentage, BigDecimal result) {

}
