package com.example.dynamiccalculation;

import com.example.dynamiccalculation.service.DynamicCalculationServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.SQLException;
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ContextConfiguration(initializers = RepositoryTest.Initializer.class)
public class RepositoryTest {
    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:11.1")
            .withDatabaseName("formulas")
            .withUsername("postgres")
            .withPassword("password")
            .withExposedPorts(5432).withInitScript("db.sql");

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                    String.format("spring.datasource.url=jdbc:postgresql://localhost:%d/formulas", postgres.getFirstMappedPort()),
                    "spring.datasource.username=postgres",
                    "spring.datasource.password=password"
            ).applyTo(applicationContext);
        }
    }

    static {
        postgres.start();
    }

    @Autowired
    private DynamicCalculationServiceImpl service;

    @Test
    public void checkFormulaIdWhenInsertExistsThenReturnsFormula() throws SQLException, ClassNotFoundException {
        service.createFormula(20.002);
        Assertions.assertEquals(1, service.createFormula(20.002).getId());
    }

//    @Test
//    public void checkCorrectDataFromGetRequest() {
//        var formula = formulaRepository.getFormula(2);
//        Assertions.assertEquals(22.12, formula.getNumber());
//        Assertions.assertEquals("двадцать двe целые двенадцать сотых", formula.getFormula());
//    }
}