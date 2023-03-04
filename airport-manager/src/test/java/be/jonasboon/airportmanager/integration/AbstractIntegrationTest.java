package be.jonasboon.airportmanager.integration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
public class AbstractIntegrationTest {
    private static PostgreSQLContainer POSTGRES;

    static {
      POSTGRES = new PostgreSQLContainer(DockerImageName.parse("postgres:latest"))
              .withDatabaseName("airport-manager")
              .withPassword("sa")
              .withUsername("sa");
              POSTGRES.start();
    }

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.username", () -> "sa");
        dynamicPropertyRegistry.add("spring.datasource.password", () -> "sa");
        dynamicPropertyRegistry.add("spring.datasource.url", () -> POSTGRES.getJdbcUrl());
    }
}
