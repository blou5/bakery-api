package org.example.dto.mapper;

import org.example.dto.request.create.VariableExpenseItemCreateDTO;
import org.example.dto.request.update.VariableExpenseItemUpdateDTO;
import org.example.entity.VariableExpenseHeader;
import org.example.entity.VariableExpenseItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VariableExpenseItemMapper {
    @Mapping(target = "itemId", ignore = true)
    @Mapping(target = "expenseHeader", expression = "java(mapExpenseFromId(dto.getExpenseHeader()))")
// Prevent recursion
    VariableExpenseItem toEntity(VariableExpenseItemCreateDTO dto);

    default VariableExpenseHeader mapExpenseFromId(Integer expenseId) {
        if (expenseId == null) return null;
        return VariableExpenseHeader.builder().expenseId(expenseId).build();
    }



    VariableExpenseItem updateEntity(VariableExpenseItemUpdateDTO updateDTO);
}
