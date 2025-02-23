package com.inditex.inditexcodechallenge.application.port.output;

import java.time.LocalDateTime;
import java.util.Optional;

import com.inditex.inditexcodechallenge.domain.ProductPrice;

public interface ProductPriceRepository {
    Optional<ProductPrice> findApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
