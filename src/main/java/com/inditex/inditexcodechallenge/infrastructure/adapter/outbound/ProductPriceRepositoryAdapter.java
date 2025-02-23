package com.inditex.inditexcodechallenge.infrastructure.adapter.outbound;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.inditex.inditexcodechallenge.application.port.output.ProductPriceRepository;
import com.inditex.inditexcodechallenge.domain.ProductPrice;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductPriceRepositoryAdapter implements ProductPriceRepository {
    private final ProductPriceJpaRepository productPriceJpaRepository;

    @Override
    public Optional<ProductPrice> findApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        return productPriceJpaRepository.findApplicablePrice(productId, brandId, applicationDate)
                .map(ProductPriceMapper::toDomain);
    }
}
