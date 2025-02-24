package com.inditex.inditexcodechallenge.domain.api;

import java.time.LocalDateTime;
import java.util.Optional;

import com.inditex.inditexcodechallenge.domain.model.ProductPrice;

public interface GetProductPriceUseCase {
    Optional<ProductPrice> getProductPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
