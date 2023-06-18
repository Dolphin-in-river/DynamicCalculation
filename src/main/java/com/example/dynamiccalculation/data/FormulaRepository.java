package com.example.dynamiccalculation.data;

import io.spring.guides.gs_producing_web_service.Formula;
import io.spring.guides.gs_producing_web_service.FormulaResponse;

public interface FormulaRepository {
    public int save(Formula result, double inputNumber);

    public FormulaResponse getFormula(int id);

    public FormulaResponse formulaExist(double number);
}
