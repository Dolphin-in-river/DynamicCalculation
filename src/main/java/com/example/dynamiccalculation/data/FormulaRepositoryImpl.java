package com.example.dynamiccalculation.data;

import io.spring.guides.gs_producing_web_service.Formula;
import io.spring.guides.gs_producing_web_service.FormulaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FormulaRepositoryImpl implements FormulaRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int save(Formula result) {
        jdbcTemplate.update("INSERT INTO formulas (number, formula) VALUES (?, ?)", result.getNumber(), result.getFormula());
        return jdbcTemplate.queryForObject("SELECT lastval()", Integer.class);
    }
    public FormulaResponse getFormula(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM formulas WHERE id=?", new Object[] {id},
                new BeanPropertyRowMapper<>(FormulaResponse.class));
    }
    public FormulaResponse formulaExist(double number) {
        List<FormulaResponse> formulas = jdbcTemplate.query("SELECT * FROM formulas WHERE number=?", new Object[]{number},
                new BeanPropertyRowMapper<>(FormulaResponse.class));
        if (formulas.isEmpty()) {
            return null;
        }
        else {
            return formulas.get(0);
        }
    }
}
