package com.tenpo.challenge_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CallHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "endpoint", nullable = true)
    private String endpoint;

    @Column(name = "timestamp", nullable = true)
    private LocalDateTime timestamp;

    @Column(name = "parameters", nullable = true)
    private String parameters;

    @Column(name = "response", nullable = true)
    private String response;
}
