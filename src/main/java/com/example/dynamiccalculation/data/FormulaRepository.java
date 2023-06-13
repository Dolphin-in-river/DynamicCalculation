package com.example.dynamiccalculation.data;
import io.spring.guides.gs_producing_web_service.FormulaResponse1;
import com.example.dynamiccalculation.dto.FormulaResponse;
import com.example.dynamiccalculation.entity.Formula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FormulaRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int save(Formula result, double inputNumber) {
        jdbcTemplate.update("INSERT INTO formulas (number, formula) VALUES (?, ?)", inputNumber, result.getFormula());
        return jdbcTemplate.queryForObject("SELECT lastval()", Integer.class);
    }
    public FormulaResponse1 getFormula(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM formulas WHERE id=?", new Object[] {id},
                new BeanPropertyRowMapper<>(FormulaResponse1.class));
    }
    public FormulaResponse1 formulaExist(double number) {
        List<FormulaResponse1> formulas = jdbcTemplate.query("SELECT * FROM formulas WHERE number=?", new Object[]{number},
                new BeanPropertyRowMapper<>(FormulaResponse1.class));
        if (formulas.isEmpty()) {
            return null;
        }
        else {
            return formulas.get(0);
        }
    }
}
