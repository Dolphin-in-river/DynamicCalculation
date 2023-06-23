package com.example.dynamiccalculation.service;

import io.spring.guides.gs_producing_web_service.FormulaResponse;
import com.example.dynamiccalculation.data.FormulaRepositoryImpl;
import io.spring.guides.gs_producing_web_service.Formula;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Service
@Validated
public class DynamicCalculationServiceImpl implements DynamicCalculationService {
    @Autowired
    private NumberTranslateService numberTranslateService;

    @Autowired
    private FormulaRepositoryImpl formulaRepositoryImpl;

    public FormulaResponse createFormula(@Valid @DecimalMin("0") @DecimalMax("1000000000") double number) {
        try {
            FormulaResponse formulaResponse = formulaRepositoryImpl.formulaExist(number);
            if (formulaResponse == null) {
                String translate = numberTranslateService.getTranslate(number);
                Formula formula = new Formula();
                formula.setFormula(translate);
                formula.setNumber(number);
                int id = formulaRepositoryImpl.save(formula);
                FormulaResponse response = new FormulaResponse();
                response.setId(id);
                response.setNumber(formula.getNumber());
                response.setFormula(formula.getFormula());
                return response;
            } else {
                return formulaResponse;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public FormulaResponse getFormula(@Valid @Min(0) @Digits(integer = 10000, fraction = 0) int id) {
        try {
            return formulaRepositoryImpl.getFormula(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("Formula not found with id " + id);
        }
    }
}
