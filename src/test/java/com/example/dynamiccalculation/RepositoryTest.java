package com.example.dynamiccalculation;

import com.example.dynamiccalculation.data.FormulaRepositoryImpl;
import io.spring.guides.gs_producing_web_service.Formula;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@RunWith(SpringRunner.class)
@SpringBootTest
@Testcontainers
public class RepositoryTest {

//    @Container
//    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
//            .withDatabaseName("testdb")
//            .withUsername("testuser")
//            .withPassword("testpassword");

    @Autowired
    private FormulaRepositoryImpl formulaRepository;

    @Test
    public void checkFormulaIdWhenInsertExistsThenReturnsFormula() {
        Formula formula = new Formula();
        formula.setNumber(20.002);
        formula.setFormula("двадцать целых две тысячные");
        var id = formulaRepository.save(formula, 20.002);
        Assertions.assertEquals(id, formulaRepository.formulaExist(20.002).getId());
    }

    @Test
    public void checkCorrectDataFromGetRequest() {
        var formula = formulaRepository.getFormula(2);
        Assertions.assertEquals(22.12, formula.getNumber());
        Assertions.assertEquals("двадцать двe целые двенадцать сотых", formula.getFormula());
    }
}
