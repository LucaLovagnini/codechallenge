package com.inditex.inditexcodechallenge.application.service;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import com.inditex.inditexcodechallenge.application.port.output.ProductPriceRepository;
import com.inditex.inditexcodechallenge.domain.ProductPrice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ProductPriceServiceTest {

    @Mock
    private ProductPriceRepository productPriceRepository;

    @InjectMocks
    private ProductPriceService productPriceService;

    @Test
    public void testGetProductPrice() {
        Long productId = 1L;
        Long brandId = 1L;
        LocalDateTime applicationDate = LocalDateTime.now();
    
        Optional<ProductPrice> expectedOptional = Optional.of(ProductPrice.builder()
        .productId(1L)
        .brandId(1L)
        .priceList(1L)
        .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
        .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
        .price(BigDecimal.valueOf(35.50))
        .curr("EUR")
        .build());

        when(productPriceRepository.findApplicablePrice(productId, brandId, applicationDate)).thenReturn(expectedOptional);

        Optional<ProductPrice> actualOptional = productPriceService.getProductPrice(productId, brandId, applicationDate);

        assertEquals(expectedOptional, actualOptional);
    }

    @Test
    public void testGetProductPriceNotFound() {
        Long productId = 1L;
        Long brandId = 1L;
        LocalDateTime applicationDate = LocalDateTime.now();

        when(productPriceRepository.findApplicablePrice(productId, brandId, applicationDate)).thenReturn(Optional.empty());

        Optional<ProductPrice> actualOptional = productPriceService.getProductPrice(productId, brandId, applicationDate);

        assertEquals(Optional.empty(), actualOptional);
    }
}