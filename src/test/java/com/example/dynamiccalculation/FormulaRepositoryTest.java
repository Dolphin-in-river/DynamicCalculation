//package com.example.dynamiccalculation;
//
//import com.example.dynamiccalculation.data.FormulaRepository;
//import com.example.dynamiccalculation.data.FormulaRepositoryImpl;
//import io.spring.guides.gs_producing_web_service.Formula;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
//import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
//import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
//@RunWith(SpringRunner.class)
//@ActiveProfiles("test")
//@DataJdbcTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@AutoConfigureDataJdbc
//public class FormulaRepositoryTest {
//    @Autowired
//    private FormulaRepositoryImpl formulaRepository;
//    @Test
//    public void givenFormula_whenInsertExists_thenReturnsFormula() {
//        Formula formula = new Formula();
//        formula.setNumber(20.002);
//        formula.setFormula("двадцать целых две тысячные");
//        var id = formulaRepository.save(formula, 20.002);
//        Assertions.assertEquals(id, formulaRepository.formulaExist(20.002).getId());
//    }
//}
