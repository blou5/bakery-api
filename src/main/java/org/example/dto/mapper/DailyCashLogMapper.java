package org.example.dto.mapper;

import org.example.dto.request.create.DailyCashLogCreateDTO;
import org.example.entity.DailyCashLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel  = "spring")
public interface DailyCashLogMapper {

    @Mapping(target = "logId", ignore = true)
    DailyCashLog toEntity(DailyCashLogCreateDTO dto);

}
