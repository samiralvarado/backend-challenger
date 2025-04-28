package com.tenpo.challenge_backend.service;

import com.tenpo.challenge_backend.model.CallHistory;
import com.tenpo.challenge_backend.repository.CallHistoryRepository;
import lombok.RequiredArgsConstructor;
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

    public Mono<Void> saveCallAsync(String endpoint, String params, String result) {
        CallHistory history = new CallHistory(null, endpoint, LocalDateTime.now(), params, result);
        return Mono.fromRunnable(() -> callHistoryRepository.save(history));
    }

    public Flux<CallHistory> getHistory(Pageable pageable) {
        return Flux.fromIterable(callHistoryRepository.findAll(pageable));
    }
}
