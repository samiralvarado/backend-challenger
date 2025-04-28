package com.tenpo.challenge_backend.service;

import com.tenpo.challenge_backend.dto.CalculationRequest;
import com.tenpo.challenge_backend.dto.CalculationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CalculationService {

    private final ExternalPercentageService externalPercentageService;
    private final HistoryService historyService;

    public Mono<CalculationResponse> calculateSumWithPercentage(CalculationRequest request) {
        return externalPercentageService.getPercentage()
                .map(percentage -> {
                    BigDecimal sum = request.num1().add(request.num2());
                    BigDecimal result = sum.add(sum.multiply(percentage));
                    return new CalculationResponse(sum, percentage, result);
                })
                .flatMap(response -> historyService.saveCallAsync("/api/calculations", request.toString(), response.toString())
                        .thenReturn(response));
    }
}