### Requirements

- For local testing, be sure to have Java 17 installed and in usage:
  - Install SDKMAN! (or jenv)
  - `sdk install java 17.0.14-amzn`
  - `sdk use java 17.0.14-amzn`
  - Check installation with `sdk current java`
- Docker installed.

### Installation

**IMPORTANT:** you can not run both local and Docker versions at the same time.

- Local testing:

  - `./mvnw clean install`
  - `./mvnw spring-boot:run`
  - You can access the H2 db at http://localhost:8080/h2-console/ with
    - **Driver Class**: `org.h2.Driver`
    - **JDBC URL**: `jdbc:h2:mem:testdb`
    - **User Name**: `sa`
    - **Password**: (leave it blank)

- Docker:
  - Run Docker on your machine
  - `docker-compose up --build`
  - **Note:** you can't access h2-console when running on Docker

### Endpoint

- Endpoint: http://localhost:8080/product-price
- Example: http://localhost:8080/product-price/35455?brandId=1&applicationDate=2020-06-16T21:00:0

### Testing

**Tests are executed at building time** in both local (`./mvnw clean install`) and Docker (`mvn clean package` in `Dockerfile`) workflows.

Notice that tests process include:

- Unit tests (`src/test`)
- Code coverage (JaCoCo with 80% minimum coverage, current coverage 94%)
- E2E tests (`com.inditex.inditexcodechallenge.e2e`, using Karate)

### Additional Information

The project follows a standard Spring Boot structure with the following key packages:

- `com.inditex.inditexcodechallenge.application`: Contains the application logic, ports and service classes
- `com.inditex.inditexcodechallenge.domain`: Contains the domain models
- `com.inditex.inditexcodechallenge.infrastructure`: Contains adapters and beans

