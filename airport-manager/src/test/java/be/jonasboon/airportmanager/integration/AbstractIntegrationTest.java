package be.jonasboon.airportmanager.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.io.UnsupportedEncodingException;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AbstractIntegrationTest {
    private static PostgreSQLContainer POSTGRES;
    protected MockMvc mockMvc;
    protected static ObjectMapper objectMapper = new ObjectMapper();

    public AbstractIntegrationTest(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

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

    public static String toJson(final Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromMvcResult(MvcResult mvcResult, Class<T> clazz) {
        try {
            return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), clazz);
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
