package com.inditex.inditexcodechallenge.domain.logic;

import java.time.LocalDateTime;
import java.util.Optional;

import com.inditex.inditexcodechallenge.domain.api.GetProductPriceUseCase;
import com.inditex.inditexcodechallenge.domain.spi.ProductPriceRepository;
import com.inditex.inditexcodechallenge.domain.model.ProductPrice;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductPriceService implements GetProductPriceUseCase {
    private final ProductPriceRepository productPriceRepository;
       

    @Override
    public Optional<ProductPrice> getProductPrice(
            @Positive(message = "Product id should be positive") Long productId, 
            @Positive(message = "Brand id should be positive") Long brandId, 
            @NotNull LocalDateTime applicationDate) {
        return productPriceRepository.findApplicablePrice(productId, brandId, applicationDate);
    }

}
