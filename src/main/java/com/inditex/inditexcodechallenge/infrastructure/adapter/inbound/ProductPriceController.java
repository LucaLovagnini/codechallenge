package com.inditex.inditexcodechallenge.infrastructure.adapter.inbound;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.inditex.inditexcodechallenge.application.port.input.GetProductPriceUseCase;
import com.inditex.inditexcodechallenge.domain.ProductPrice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/product-price")
@RequiredArgsConstructor
@Validated
@Tag(name = "Price", description = "Product Price API")
public class ProductPriceController {
    private final GetProductPriceUseCase getProductPriceUseCase;

    @GetMapping("/{id}")
    @Operation(summary = "Get product price by date", description = "Returns product price by brand and date")    
    public ResponseEntity<ProductPrice> getProductPrice(
            @PathVariable @Positive (message = "Product id should be positive") Long id, 
            @RequestParam(required = true) @Positive(message = "Brand id should be positive") Long brandId, 
            @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {                
        return getProductPriceUseCase.getProductPrice(id, brandId, applicationDate)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + id + " not found");
                });
    }
    
}

