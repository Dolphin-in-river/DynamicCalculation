package com.example.dynamiccalculation.service;

import io.spring.guides.gs_producing_web_service.FormulaResponse1;
import com.example.dynamiccalculation.dto.FormulaResponse;
import com.example.dynamiccalculation.entity.Formula;

import java.sql.SQLException;

public interface DynamicCalculationService {
    FormulaResponse1 createFormula(double formula) throws SQLException, ClassNotFoundException;
}
