package com.tenpo.challenge_backend.repository;

import com.tenpo.challenge_backend.model.CallHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;


public interface CallHistoryRepository extends JpaRepository<CallHistory, Long> {
    Page<CallHistory> findAll(Pageable pageable);

}
