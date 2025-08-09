package org.example.dto.mapper;

import org.example.dto.request.update.VariableExpenseHeaderUpdateDTO;
import org.example.dto.request.create.VariableExpenseHeaderCreateDTO;
import org.example.dto.response.VariableExpenseHeaderResponseDTO;
import org.example.entity.DailyCashLog;
import org.example.entity.VariableExpenseHeader;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel  = "spring")

public interface VariableExpenseHeaderMapper {

    @Mapping(target = "expenseId", ignore = true)
    @Mapping(target = "log", expression = "java(mapLogFromId(dto.getLogId()))")
    @Mapping(target = "expenseHeaders", ignore = true) // Prevent recursion
    VariableExpenseHeader toEntity(VariableExpenseHeaderCreateDTO dto);

    default DailyCashLog mapLogFromId(Integer logId) {
        if (logId == null) return null;
        DailyCashLog log = DailyCashLog.builder().logId(logId).build();
        return log;
    }

    @Mapping(target = "expenseHeaders", ignore = true)
    VariableExpenseHeader toEntity(VariableExpenseHeaderUpdateDTO dto);

    VariableExpenseHeaderResponseDTO toDTO(VariableExpenseHeader variableExpenseHeader);
}
