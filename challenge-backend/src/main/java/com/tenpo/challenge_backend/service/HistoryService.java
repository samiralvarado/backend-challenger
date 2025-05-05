package com.tenpo.challenge_backend.service;

import com.tenpo.challenge_backend.dto.CallHistoryDto;
import com.tenpo.challenge_backend.dto.CallHistoryMapper;
import com.tenpo.challenge_backend.exception.EntityNotFoundException;
import com.tenpo.challenge_backend.exception.ServicioExternoException;
import com.tenpo.challenge_backend.model.CallHistory;
import com.tenpo.challenge_backend.repository.CallHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final CallHistoryRepository callHistoryRepository;
    private final CallHistoryMapper callHistoryMapper;

    public Mono<Void> saveCallAsync(String endpoint, String params, String result) {
        if (endpoint == null || endpoint.isEmpty()) {
            throw new ServicioExternoException("El servicio externo no está disponible.");
        }

        CallHistory history = new CallHistory(null, endpoint, LocalDateTime.now(), params, result);
        return Mono.fromRunnable(() -> callHistoryRepository.save(history));
    }

    // Obtener historial con paginación
    public Flux<CallHistoryDto> getHistory(Pageable pageable) {
        Page<CallHistory> page = callHistoryRepository.findAll(pageable);
        if (page.isEmpty()) {
            throw new EntityNotFoundException("No se encontró el historial de llamadas.");
        }
        return Flux.fromIterable(page.getContent())
                .map(callHistoryMapper::toDto);
    }
}
