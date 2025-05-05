package com.tenpo.challenge_backend;

import com.tenpo.challenge_backend.service.ExternalPercentageService;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

class ExternalPercentageServiceTest {

    private final ExternalPercentageService service = new ExternalPercentageService();

    @Test
    void shouldReturnPercentage() {
        StepVerifier.create(service.getPercentage())
                .expectNext(new BigDecimal("0.15"))
                .verifyComplete();
    }
}