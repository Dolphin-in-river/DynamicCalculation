package com.example.dynamiccalculation.entity;
import lombok.*;

@Getter
@Setter
public class Formula {
    private double number;
    private String formula;
    public Formula(double number, String formula) {
        this.number = number;
        this.formula = formula;
    }
}
