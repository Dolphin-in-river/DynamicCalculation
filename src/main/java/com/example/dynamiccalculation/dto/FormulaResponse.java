package com.example.dynamiccalculation.dto;

import com.example.dynamiccalculation.entity.Formula;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormulaResponse {
    private int id;
    private double number;
    private String formula;

    public FormulaResponse(Formula formula, int id) {
        this.id = id;
        this.number = formula.getNumber();
        this.formula = formula.getFormula();
    }

    public FormulaResponse() {
    }
}
