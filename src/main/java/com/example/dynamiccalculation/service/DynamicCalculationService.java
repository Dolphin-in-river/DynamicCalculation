package com.example.dynamiccalculation.service;

import io.spring.guides.gs_producing_web_service.FormulaResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.sql.SQLException;

public interface DynamicCalculationService {
    FormulaResponse createFormula(@Valid @DecimalMin("0") @DecimalMax("1000000000") double formula) throws SQLException, ClassNotFoundException;

    FormulaResponse getFormula(@Valid @Min(0) @Digits(integer = 10000, fraction = 0) int id);
}
