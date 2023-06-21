package com.example.dynamiccalculation.data;

import io.spring.guides.gs_producing_web_service.Formula;
import io.spring.guides.gs_producing_web_service.FormulaResponse;

public interface FormulaRepository {
    int save(Formula result, double inputNumber);

    FormulaResponse getFormula(int id);

    FormulaResponse formulaExist(double number);
}
