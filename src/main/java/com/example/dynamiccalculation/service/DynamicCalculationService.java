package com.example.dynamiccalculation.service;

import com.concretepage.gs_ws.FormulaResponse1;
import com.example.dynamiccalculation.dto.FormulaResponse;
import com.example.dynamiccalculation.entity.Formula;

import java.sql.SQLException;

public interface DynamicCalculationService {
    FormulaResponse1 createFormula(double formula) throws SQLException, ClassNotFoundException;
}
