package com.inditex.inditexcodechallenge.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.inditex.inditexcodechallenge.application.port.input.GetProductPriceUseCase;
import com.inditex.inditexcodechallenge.application.port.output.ProductPriceRepository;
import com.inditex.inditexcodechallenge.application.service.ProductPriceService;

@Configuration
public class ApplicationConfig {

    @Bean
    ProductPriceService productPriceService(ProductPriceRepository productPriceRepository) {
        return new ProductPriceService(productPriceRepository);
    }

    @Bean
    GetProductPriceUseCase getProductPriceUseCase(ProductPriceService productPriceService) {
        return productPriceService;
    }
}
