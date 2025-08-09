package org.example.dto.mapper;

import org.example.dto.request.create.ProductionCreateDTO;
import org.example.dto.request.update.ProductProductionUpdate;
import org.example.dto.response.ProductionDto;
import org.example.entity.DailyCashLog;
import org.example.entity.Product;
import org.example.entity.ProductProduction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductionMapper {

    @Mapping(target = "productId", source = "productProduction.product.productId")
    @Mapping(target = "logId", source = "log.logId")
    @Mapping(target = "productionDate", source = "log.logDate")
    @Mapping(target = "productName", source = "product.productName")

    ProductionDto toDto(ProductProduction productProduction);


    @Mapping(target = "product", expression = "java(mapProductFromId(productionDto.getProductId()))")
    @Mapping(target = "log", expression ="java(mapLogFromId(productionDto.getLogId()))" )
    ProductProduction dtoToEntity(ProductProductionUpdate productionDto);

    @Mapping(target = "productionId", ignore = true)
    @Mapping(target = "product", expression = "java(mapProductFromId(productionDto.getProductId()))")
    @Mapping(target = "log", expression = "java(mapLogFromId(productionDto.getLogId()))")
    ProductProduction toEntity(ProductionCreateDTO productionDto);


    default DailyCashLog mapLogFromId(Integer logId) {
        if (logId == null) return null;
        DailyCashLog log = DailyCashLog.builder().logId(logId).build();
        return log;
    }


    default Product mapProductFromId(Integer productId) {
        if (productId == null) return null;
        Product product = Product.builder().productId(productId).build();
        return product;
    }

}
