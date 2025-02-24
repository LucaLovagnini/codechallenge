package com.inditex.inditexcodechallenge.infrastructure.h2;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductPriceJpaRepository extends JpaRepository<ProductPriceEntity, Long> {
    @Query("SELECT p FROM ProductPriceEntity p WHERE p.productId = :productId AND p.brandId = :brandId AND :applicationDate BETWEEN p.startDate AND p.endDate ORDER BY p.priceList DESC LIMIT 1")
    Optional<ProductPriceEntity> findPriceByProductBrandAndDate(Long productId, Long brandId, LocalDateTime applicationDate);
}