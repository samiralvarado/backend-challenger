package com.tenpo.challenge_backend.repository;

import com.tenpo.challenge_backend.model.CallHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallHistoryRepository extends JpaRepository<CallHistory, Long> {

}
