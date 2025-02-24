package com.inditex.inditexcodechallenge.infrastructure.h2;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.inditex.inditexcodechallenge.domain.spi.ProductPriceRepository;
import com.inditex.inditexcodechallenge.domain.model.ProductPrice;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductPriceRepositoryAdapter implements ProductPriceRepository {
    private final ProductPriceJpaRepository productPriceJpaRepository;

    @Override
    public Optional<ProductPrice> findApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        return productPriceJpaRepository.findPriceByProductBrandAndDate(productId, brandId, applicationDate)
                .map(ProductPriceMapper::toDomain);
    }
}
