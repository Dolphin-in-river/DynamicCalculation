package com.example.dynamiccalculation.controller;

import io.spring.guides.gs_producing_web_service.CreateFormulaRequest;
import io.spring.guides.gs_producing_web_service.FormulaResponse;

import com.example.dynamiccalculation.service.DynamicCalculationServiceImpl;
import io.spring.guides.gs_producing_web_service.GetFormulaRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@Validated
public class DynamicCalculationController {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    private DynamicCalculationServiceImpl calculatorService;

    @Autowired
    public DynamicCalculationController(DynamicCalculationServiceImpl calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createFormulaRequest")
    @ResponsePayload
    public FormulaResponse createFormulaRequest(@Valid @RequestPayload CreateFormulaRequest request) {
        return calculatorService.createFormula(Double.parseDouble(request.getFormula()));
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getFormulaRequest")
    @ResponsePayload
    public FormulaResponse getFormulaRequest(@Valid @RequestPayload GetFormulaRequest request) {
        return calculatorService.getFormula(Integer.parseInt(request.getId()));
    }
}
