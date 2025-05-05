package com.tenpo.challenge_backend.service;

import com.tenpo.challenge_backend.dto.CalculationRequest;
import com.tenpo.challenge_backend.dto.CalculationResponse;
import com.tenpo.challenge_backend.exception.ExternalServiceException;
import com.tenpo.challenge_backend.exception.InvalidCalculationException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.naming.ServiceUnavailableException;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CalculationService {

    private final ExternalPercentageService externalPercentageService;
    private final HistoryService historyService;

    public Mono<CalculationResponse> calculateSumWithPercentage(CalculationRequest request) {
        if (request.num1() == null || request.num2() == null) {
            return Mono.error(new InvalidCalculationException("Los números de entrada no pueden ser nulos."));
        }

        return externalPercentageService.getPercentage()
                .onErrorResume(ex -> Mono.error(new ExternalServiceException("No se pudo obtener el porcentaje desde el servicio externo.")))
                .map(percentage -> {
                    BigDecimal sum = request.num1().add(request.num2());
                    BigDecimal result = sum.add(sum.multiply(percentage));
                    return new CalculationResponse(sum, percentage, result);
                })
                .flatMap(response -> historyService.saveCallAsync("/api/calculations", request.toString(), response.toString())
                        .thenReturn(response))
                .onErrorResume(ex -> Mono.error(new ServiceUnavailableException("Error al procesar la operación de cálculo.")));
    }
}