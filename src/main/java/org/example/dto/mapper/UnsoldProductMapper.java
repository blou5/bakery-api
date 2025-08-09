package org.example.dto.mapper;

import org.example.dto.request.create.UnsoldProductCreateDTO;
import org.example.dto.response.UnsoldProductDto;
import org.example.entity.DailyCashLog;
import org.example.entity.Product;
import org.example.entity.UnsoldProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface UnsoldProductMapper {

    @Mapping(target = "unsoldId", ignore = true)
    @Mapping(target = "product", expression = "java(mapProductFromId(unsoldProductCreateDTO.getProductId()))")
    @Mapping(target = "log", expression = "java(mapLogFromId(unsoldProductCreateDTO.getLogId()))")
    UnsoldProduct toEntity(UnsoldProductCreateDTO unsoldProductCreateDTO);


     @Mapping(target = "productName", source = "unsoldProduct.product.productName")
     @Mapping(target = "productId", source = "unsoldProduct.product.productId")
     @Mapping(target = "logId", source = "unsoldProduct.log.logId")
     @Mapping(target = "logDate", expression = "java(getDateFromLog(unsoldProduct.getLog()))")
    UnsoldProductDto toDto(UnsoldProduct unsoldProduct);

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
    default LocalDate getDateFromLog(DailyCashLog log){
        if (log.getLogId() == null) return null;
        return log.getLogDate();
    }

}
