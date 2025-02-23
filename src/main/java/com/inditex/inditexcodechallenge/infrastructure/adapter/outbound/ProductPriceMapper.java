package com.inditex.inditexcodechallenge.infrastructure.adapter.outbound;

import com.inditex.inditexcodechallenge.domain.ProductPrice;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductPriceMapper {
    public ProductPrice toDomain(ProductPriceEntity productPriceEntity) {
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
