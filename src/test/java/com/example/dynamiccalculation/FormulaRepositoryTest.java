package com.example.dynamiccalculation;

import com.example.dynamiccalculation.data.FormulaRepositoryImpl;
import io.spring.guides.gs_producing_web_service.Formula;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJdbcTest
@ComponentScan("com.example.dynamiccalculation")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FormulaRepositoryTest {
    public static final double VALUE = 3213.32112;
    @Autowired
    private FormulaRepositoryImpl formulaRepository;

    @Test
    public void givenFormula_whenInsertExists_thenReturnsFormula() {
        Formula formula = new Formula();
        formula.setNumber(VALUE);
        String formulaTranslate = "три тысячи двести тринадцать целых тридцать две тысячи сто двенадцать стотысячных";
        formula.setFormula(formulaTranslate);
        var id = formulaRepository.save(formula, 3213.32112);
        Assertions.assertEquals(formulaTranslate, formulaRepository.getFormula(id).getFormula());
    }
}
