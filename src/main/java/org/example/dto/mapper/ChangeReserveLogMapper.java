package org.example.dto.mapper;


import org.example.dto.request.create.ChangeReserveLogRequestDTO;
import org.example.entity.ChangeReserveLog;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ChangeReserveLogMapper {

    @Mapping(target = "amount", expression = "java(dto.getDenomination() * dto.getQuantity())")
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "createdAt", ignore = true) // handled by @PrePersist
    @Mapping(target = "statusChangedAt", ignore = true)
    @Mapping(target = "reserveLogId", ignore = true)
    ChangeReserveLog toEntity(ChangeReserveLogRequestDTO dto);
}
