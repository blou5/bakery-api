package org.example.dto.mapper;

import org.example.dto.request.create.ProductCreateDTO;
import org.example.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "productId", ignore = true)
    Product toEntity(ProductCreateDTO dto);
}
