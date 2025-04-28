package com.tenpo.challenge_backend.controller;

import com.tenpo.challenge_backend.model.CallHistory;
import com.tenpo.challenge_backend.service.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/history")
@Tag(name = "Historial", description = "Operaciones relacionadas con cálculos matemáticos")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;
    @Operation(
            summary = "Obtener todo el historial de llamadas",
            description = "Este endpoint devuelve todos los registros del historial de llamadas. Puedes usar los parámetros de paginación para obtener resultados limitados por página.",
            parameters = {
                    @Parameter(name = "page", description = "Número de página", in = ParameterIn.QUERY, required = false),
                    @Parameter(name = "size", description = "Tamaño de la página", in = ParameterIn.QUERY, required = false),
                    @Parameter(name = "sort", description = "Ordenar resultados", in = ParameterIn.QUERY, required = false)
            }
    )
    @GetMapping
    public Flux<CallHistory> getAllHistory(Pageable pageable) {
        return historyService.getHistory(pageable);
    }
}