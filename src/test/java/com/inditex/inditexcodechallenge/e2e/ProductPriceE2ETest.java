package com.inditex.inditexcodechallenge.e2e;

import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) // Start Spring Boot before tests
class ProductPriceE2ETest {

    @Karate.Test
    Karate testProductPrice() {
        return Karate.run("ProductPriceE2ETest").relativeTo(getClass());
    }
}
