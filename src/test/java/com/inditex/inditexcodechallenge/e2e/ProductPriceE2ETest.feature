Feature: Product Price API E2E Test

  Background:
    * url 'http://localhost:8080'

  Scenario: Test 1 - Get product price at 2020-06-14T10:00:00
    Given path 'product-price', 35455
    And param brandId = 1
    And param applicationDate = '2020-06-14T10:00:00'
    When method get
    Then status 200
    And match response.productId == 35455
    And match response.brandId == 1
    And match response.price == 35.50
    And match response.priceList == 1
    And match response.curr == 'EUR'
    And match response.startDate == '2020-06-14T00:00:00'
    And match response.endDate == '2020-12-31T23:59:59'

  Scenario: Test 2 - Get product price at 2020-06-14T16:00:00
    Given path 'product-price', 35455
    And param brandId = 1
    And param applicationDate = '2020-06-14T16:00:00'
    When method get
    Then status 200
    And match response.productId == 35455
    And match response.brandId == 1
    And match response.price == 25.45
    And match response.priceList == 2
    And match response.curr == 'EUR'
    And match response.startDate == '2020-06-14T15:00:00'
    And match response.endDate == '2020-06-14T18:30:00'  

  Scenario: Test 3 - Get product price at 2020-06-14T21:00:00
    Given path 'product-price', 35455
    And param brandId = 1
    And param applicationDate = '2020-06-14T21:00:00'
    When method get
    Then status 200
    And match response.productId == 35455
    And match response.brandId == 1
    And match response.price == 35.50
    And match response.priceList == 1
    And match response.curr == 'EUR'
    And match response.startDate == '2020-06-14T00:00:00'
    And match response.endDate == '2020-12-31T23:59:59'

  Scenario: Test 4 - Get product price at 2020-06-15T10:00:00
    Given path 'product-price', 35455
    And param brandId = 1
    And param applicationDate = '2020-06-15T10:00:00'
    When method get
    Then status 200
    And match response.productId == 35455
    And match response.brandId == 1
    And match response.price == 30.50
    And match response.priceList == 3
    And match response.curr == 'EUR'
    And match response.startDate == '2020-06-15T00:00:00'
    And match response.endDate == '2020-06-15T11:00:00'

  Scenario: Test 5 - Get product price at 2020-06-16T21:00:00
    Given path 'product-price', 35455
    And param brandId = 1
    And param applicationDate = '2020-06-16T21:00:00'
    When method get
    Then status 200
    And match response.productId == 35455
    And match response.brandId == 1
    And match response.price == 38.95
    And match response.priceList == 4
    And match response.curr == 'EUR'
    And match response.startDate == '2020-06-15T16:00:00'
    And match response.endDate == '2020-12-31T23:59:59'

  Scenario: Get product price by invalid product ID
    Given path 'product-price', -1
    And param brandId = 1
    And param applicationDate = '2020-06-15T10:00:00'
    When method get
    Then status 400

  Scenario: Get product price by invalid brand ID
    Given path 'product-price', 1
    And param brandId = -1
    And param applicationDate = '2020-06-15T10:00:00'
    When method get
    Then status 400

  Scenario: Get product price by invalid date format
    Given path 'product-price', 1
    And param brandId = 1
    And param applicationDate = 'invalid-date'
    When method get
    Then status 400

  Scenario: Get product price by missing required parameter
    Given path 'product-price', 1
    And param applicationDate = '2020-06-15T10:00:00'
    When method get
    Then status 400
