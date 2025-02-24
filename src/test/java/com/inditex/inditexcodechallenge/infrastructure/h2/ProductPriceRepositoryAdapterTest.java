package com.inditex.inditexcodechallenge.infrastructure.h2;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inditex.inditexcodechallenge.domain.model.ProductPrice;

@ExtendWith(MockitoExtension.class)
public class ProductPriceRepositoryAdapterTest {

    @Mock
    private ProductPriceJpaRepository productPriceJpaRepository;

    @InjectMocks
    private ProductPriceRepositoryAdapter productPriceRepositoryAdapter;

    @Test
    public void testFindApplicablePrice() {
        Long productId = 1L;
        Long brandId = 1L;
        LocalDateTime applicationDate = LocalDateTime.now();
        ProductPriceEntity productPriceEntity = ProductPriceEntity.builder()
        .productId(1L)
        .brandId(1L)
        .priceList(1L)
        .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
        .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
        .price(BigDecimal.valueOf(35.50))
        .curr("EUR")
        .build();
        Optional<ProductPriceEntity> optionalProductPrice = Optional.of(productPriceEntity);

        when(productPriceJpaRepository.findPriceByProductBrandAndDate(productId, brandId, applicationDate))
                .thenReturn(optionalProductPrice);

        Optional<ProductPrice> result = productPriceRepositoryAdapter.findApplicablePrice(productId, brandId, applicationDate);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(ProductPriceMapper.toDomain(productPriceEntity));
    }

    @Test
    public void testFindApplicablePrice_NotFound() {
        Long productId = 1L;
        Long brandId = 1L;
        LocalDateTime applicationDate = LocalDateTime.now();

        when(productPriceJpaRepository.findPriceByProductBrandAndDate(productId, brandId, applicationDate))
                .thenReturn(Optional.empty());

        Optional<ProductPrice> result = productPriceRepositoryAdapter.findApplicablePrice(productId, brandId, applicationDate);

        assertThat(result).isNotPresent();
    }
}
