package com.tenpo.challenge_backend.controller;

import com.tenpo.challenge_backend.dto.CalculationRequest;
import com.tenpo.challenge_backend.dto.CalculationResponse;
import com.tenpo.challenge_backend.service.CalculationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/calculations")
@Tag(name = "Cálculos", description = "Operaciones relacionadas con cálculos matemáticos")
@RequiredArgsConstructor
public class CalculationController {

    private final CalculationService calculationService;

    @PostMapping

    @Operation(
            summary = "Calculo Porcentaje",
            description = "Este endpoint recibe dos números y devuelve la suma de ambos con un porcentaje calculado.",
            parameters = {
                    @Parameter(name = "request", description = "Objeto que contiene los números para realizar el cálculo", required = true)
            }
    )
    public Mono<CalculationResponse> calculate(@RequestBody CalculationRequest request) {
        return calculationService.calculateSumWithPercentage(request);
    }
}

