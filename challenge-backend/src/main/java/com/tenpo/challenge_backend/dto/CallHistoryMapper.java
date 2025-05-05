package com.tenpo.challenge_backend.dto;

import com.tenpo.challenge_backend.model.CallHistory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CallHistoryMapper {

    CallHistoryDto toDto(CallHistory entity);
}
