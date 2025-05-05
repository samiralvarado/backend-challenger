package com.tenpo.challenge_backend;

import com.tenpo.challenge_backend.dto.CallHistoryDto;
import com.tenpo.challenge_backend.dto.CallHistoryMapper;
import com.tenpo.challenge_backend.model.CallHistory;
import com.tenpo.challenge_backend.repository.CallHistoryRepository;
import com.tenpo.challenge_backend.service.HistoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import reactor.test.StepVerifier;


import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class HistoryServiceTest {

    @Mock
    private CallHistoryRepository repository;

    @Mock
    private CallHistoryMapper mapper;

    @InjectMocks
    private HistoryService service;

    @Test
    void shouldGetHistoryPaginated() {
        Pageable pageable = PageRequest.of(0, 2);
        CallHistory history1 = new CallHistory(1L, "ep", LocalDateTime.now(), "p", "r");
        CallHistory history2 = new CallHistory(2L, "ep2", LocalDateTime.now(), "p2", "r2");

        Page<CallHistory> page = new PageImpl<>(List.of(history1, history2));
        when(repository.findAll(pageable)).thenReturn(page);
        when(mapper.toDto(any())).thenReturn(new CallHistoryDto("/api/calculations",
                LocalDateTime.parse("2025-05-05T17:29:48.974066"),
                "CalculationRequest[num1=30, num2=5]",
                "CalculationResponse[sum=35, percentage=0.15, result=40.25]"));

        StepVerifier.create(service.getHistory(pageable))
                .expectNextCount(2)
                .verifyComplete();
    }
}