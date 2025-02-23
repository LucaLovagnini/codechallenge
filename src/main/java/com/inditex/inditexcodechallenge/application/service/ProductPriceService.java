package com.inditex.inditexcodechallenge.application.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.inditex.inditexcodechallenge.application.port.input.GetProductPriceUseCase;
import com.inditex.inditexcodechallenge.application.port.output.ProductPriceRepository;
import com.inditex.inditexcodechallenge.domain.ProductPrice;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductPriceService implements GetProductPriceUseCase {
    private final ProductPriceRepository productPriceRepository;

    @Override
    public Optional<ProductPrice> getProductPrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        return productPriceRepository.findApplicablePrice(productId, brandId, applicationDate);
    }

}
