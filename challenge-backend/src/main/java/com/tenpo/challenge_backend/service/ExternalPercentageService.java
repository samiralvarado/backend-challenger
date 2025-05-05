package com.tenpo.challenge_backend.service;

import com.tenpo.challenge_backend.exception.ExternalServiceException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class ExternalPercentageService {

    @Cacheable(value = "percentage", unless = "#result == null")
    public Mono<BigDecimal> getPercentage() {
        return Mono.just(new BigDecimal("0.15"))
                .onErrorResume(ex -> Mono.error(new ExternalServiceException("No se pudo obtener el porcentaje desde el servicio externo.")));
    }
}
