package org.example.dto.response;

import org.example.entity.Product;

public record ProductResponseDTO(
        Integer productId,
        String productName,
        Integer sellingPrice
) {
    public static ProductResponseDTO from(Product product) {
        return new ProductResponseDTO(
                product.getProductId(),
                product.getProductName(),
                product.getSellingPrice()
        );
    }
}
