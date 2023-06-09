package com.example.dynamiccalculation.service;
import com.concretepage.gs_ws.FormulaResponse1;
import com.example.dynamiccalculation.data.FormulaRepository;
import com.example.dynamiccalculation.dto.FormulaResponse;
import com.example.dynamiccalculation.entity.Formula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DynamicCalculationServiceImpl implements DynamicCalculationService {
    @Autowired
    private NumberTranslateService numberTranslateService;

    @Autowired
    private FormulaRepository formulaRepository;

    public FormulaResponse1 createFormula(double formula) {
        double number = formula;
        try {
            FormulaResponse1 formulaResponse = formulaRepository.formulaExist(number);
            if (formulaResponse == null) {
                String translate = numberTranslateService.getTranslate(number);
                Formula result = new Formula(number, translate);
                int id = formulaRepository.save(result, number);
                FormulaResponse1 response = new FormulaResponse1();
                response.setId(id);
                response.setNumber(result.getNumber());
                response.setFormula(result.getFormula());
                return response;
            }
            else{
                return formulaResponse;
            }

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public FormulaResponse1 getFormula(int id) {
        try {
            return formulaRepository.getFormula(id);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("Formula not found with id " + id);
        }
    }
}
