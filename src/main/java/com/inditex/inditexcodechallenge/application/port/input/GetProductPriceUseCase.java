package com.inditex.inditexcodechallenge.application.port.input;

import java.time.LocalDateTime;
import java.util.Optional;

import com.inditex.inditexcodechallenge.domain.ProductPrice;

public interface GetProductPriceUseCase {
    Optional<ProductPrice> getProductPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
