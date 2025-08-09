package org.example.dto.mapper;

import org.example.dto.request.create.WithdrawalsCreateDto;
import org.example.entity.DailyCashLog;
import org.example.entity.Withdrawals;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WithdrawalsMapper {
    @Mapping(target = "withdrawalId", ignore = true)
    @Mapping(target = "log", expression = "java(mapLogFromId(withdrawalsCreateDto.getLog().getLogId()))")
    Withdrawals toEntity(WithdrawalsCreateDto withdrawalsCreateDto);

    default DailyCashLog mapLogFromId(Integer logId) {
        if (logId == null) return null;
        return DailyCashLog.builder().logId(logId).build();
    }

}
