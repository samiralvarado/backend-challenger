package com.tenpo.challenge_backend;

import com.tenpo.challenge_backend.dto.CalculationRequest;
import com.tenpo.challenge_backend.service.CalculationService;
import com.tenpo.challenge_backend.service.ExternalPercentageService;
import com.tenpo.challenge_backend.service.HistoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import javax.naming.ServiceUnavailableException;
import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class CalculationsServiceTest {

    @Mock
    private ExternalPercentageService externalPercentageService;

    @Mock
    private HistoryService historyService;

    @InjectMocks
    private CalculationService calculationService;

    @Test
    void shouldCalculateSumWithPercentage() {
        CalculationRequest request = new CalculationRequest(BigDecimal.TEN, BigDecimal.ONE);
        BigDecimal percentage = new BigDecimal("0.10"); // 10%

        when(externalPercentageService.getPercentage()).thenReturn(Mono.just(percentage));
        when(historyService.saveCallAsync(any(), any(), any())).thenReturn(Mono.empty());

        StepVerifier.create(calculationService.calculateSumWithPercentage(request))
                .expectNextMatches(response ->
                        response.sum().compareTo(new BigDecimal("11.00")) == 0 &&
                                response.percentage().compareTo(new BigDecimal("0.10")) == 0 &&
                                response.result().compareTo(new BigDecimal("12.10")) == 0
                )
                .verifyComplete();
    }

    @Test
    void shouldHandleExternalServiceError() {
        CalculationRequest request = new CalculationRequest(BigDecimal.TEN, BigDecimal.ONE);

        when(externalPercentageService.getPercentage())
                .thenReturn(Mono.error(new RuntimeException("Servicio externo caído")));

        StepVerifier.create(calculationService.calculateSumWithPercentage(request))
                .expectErrorMatches(throwable ->
                        throwable instanceof ServiceUnavailableException &&
                                throwable.getMessage().equals("Error al procesar la operación de cálculo.")
                )
                .verify();
    }
}