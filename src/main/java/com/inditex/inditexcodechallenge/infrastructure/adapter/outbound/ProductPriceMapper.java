package com.inditex.inditexcodechallenge.infrastructure.adapter.outbound;

import com.inditex.inditexcodechallenge.domain.ProductPrice;

public class ProductPriceMapper {
    public static ProductPriceEntity toEntity(ProductPrice productPrice) {
        return ProductPriceEntity.builder()
            .id(productPrice.getId())
            .brandId(productPrice.getBrandId())
            .startDate(productPrice.getStartDate())
            .endDate(productPrice.getEndDate())
            .priceList(productPrice.getPriceList())
            .productId(productPrice.getProductId())
            .price(productPrice.getPrice())
            .curr(productPrice.getCurr())
            .build();
    }

    public static ProductPrice toDomain(ProductPriceEntity productPriceEntity) {
        return ProductPrice.builder()
            .id(productPriceEntity.getId())
            .brandId(productPriceEntity.getBrandId())
            .startDate(productPriceEntity.getStartDate())
            .endDate(productPriceEntity.getEndDate())
            .priceList(productPriceEntity.getPriceList())
            .productId(productPriceEntity.getProductId())
            .price(productPriceEntity.getPrice())
            .curr(productPriceEntity.getCurr())
            .build();
    }    
}
