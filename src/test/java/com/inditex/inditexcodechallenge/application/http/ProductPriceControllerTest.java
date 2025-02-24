package com.inditex.inditexcodechallenge.application.http;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.inditex.inditexcodechallenge.domain.api.GetProductPriceUseCase;
import com.inditex.inditexcodechallenge.domain.model.ProductPrice;

@WebMvcTest(ProductPriceController.class)
class ProductPriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GetProductPriceUseCase getProductPriceUseCase;

    private ProductPrice sampleProductPrice;

    @BeforeEach
    void setUp() {
        sampleProductPrice = ProductPrice.builder()
                .productId(1L)
                .brandId(1L)
                .priceList(1L)
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .price(BigDecimal.valueOf(35.50))
                .curr("EUR")
                .build();
    }

    @Test
    void getProductPrice_ValidRequest_ReturnsOk() throws Exception {
        when(getProductPriceUseCase.getProductPrice(anyLong(), anyLong(), any(LocalDateTime.class)))
                .thenReturn(Optional.of(sampleProductPrice));

        mockMvc.perform(get("/product-price/1")
                .param("brandId", "1")
                .param("applicationDate", "2020-06-15T10:00:00")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(jsonPath("$.curr").value("EUR"));
    }

    @Test
    void getProductPrice_ProductNotFound_ReturnsNotFound() throws Exception {
        when(getProductPriceUseCase.getProductPrice(anyLong(), anyLong(), any(LocalDateTime.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/product-price/999")
                .param("brandId", "1")
                .param("applicationDate", "2020-06-14T10:00:00")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getProductPrice_InvalidProductId_ReturnsBadRequest() throws Exception {
        mockMvc.perform(get("/product-price/-1")
                .param("brandId", "1")
                .param("applicationDate", "2020-06-14T10:00:00")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getProductPrice_InvalidBrandId_ReturnsBadRequest() throws Exception {
        mockMvc.perform(get("/product-price/1")
                .param("brandId", "-1")
                .param("applicationDate", "2020-06-14T10:00:00")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.detail").value("getProductPrice.brandId: Brand id should be positive"))  // ✅ Validate custom message
                .andExpect(status().isBadRequest());
    }

    @Test
    void getProductPrice_InvalidDateFormat_ReturnsBadRequest() throws Exception {
        mockMvc.perform(get("/product-price/1")
                .param("brandId", "1")
                .param("applicationDate", "invalid-date")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.detail").value("Failed to convert 'applicationDate' with value: 'invalid-date'"))  // ✅ Validate custom message
                .andExpect(status().isBadRequest());
    }

    @Test
    void getProductPrice_MissingRequiredParam_ReturnsBadRequest() throws Exception {
        mockMvc.perform(get("/product-price/1")
                .param("applicationDate", "2020-06-14T10:00:00")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.detail").value("Required parameter 'brandId' is not present."))  // ✅ Validate custom message
                .andExpect(status().isBadRequest());
    }
}
