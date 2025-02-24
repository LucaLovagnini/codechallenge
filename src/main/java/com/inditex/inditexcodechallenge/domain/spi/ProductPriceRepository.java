package com.inditex.inditexcodechallenge.domain.spi;

import java.time.LocalDateTime;
import java.util.Optional;

import com.inditex.inditexcodechallenge.domain.model.ProductPrice;

public interface ProductPriceRepository {
    Optional<ProductPrice> findApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
