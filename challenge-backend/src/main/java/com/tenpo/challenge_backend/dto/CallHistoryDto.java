package com.tenpo.challenge_backend.dto;

import java.time.LocalDateTime;

public record CallHistoryDto(String endpoint, LocalDateTime timestamp, String parameters, String response){
}
