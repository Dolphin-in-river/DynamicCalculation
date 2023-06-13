package com.example.dynamiccalculation.controller;

import io.spring.guides.gs_producing_web_service.CreateFormulaRequest1;
import io.spring.guides.gs_producing_web_service.FormulaResponse1;

import com.example.dynamiccalculation.data.FormulaRepository;
import com.example.dynamiccalculation.service.DynamicCalculationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class FormulaEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
    private DynamicCalculationServiceImpl calculatorService;

    @Autowired
    public FormulaEndpoint(DynamicCalculationServiceImpl calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createFormulaRequest1")
    @ResponsePayload
    public FormulaResponse1 createFormulaRequest1(@RequestPayload CreateFormulaRequest1 request) {
        FormulaResponse1 response = new FormulaResponse1();
        response = calculatorService.createFormula(request.getFormula());
        return response;
    }

//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getFormula")
//    @ResponsePayload
//    public FormulaResponse1 getFormula(@RequestPayload CreateFormulaRequest1 request) {
//        FormulaResponse1 response = new FormulaResponse1();
//        response = calculatorService.createFormula(request.getFormula());
//        return response;
//    }

//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createFormulaRequest1")
//    @ResponsePayload
//    public JAXBElement<FormulaResponse1> createFormulaRequest1(@RequestPayload JAXBElement<CreateFormulaRequest1> request) {
//        FormulaResponse1 response = new FormulaResponse1();
////        calculatorService.createFormula(request.getFormula());
//        return createJaxbElement(response, FormulaResponse1.class);
//    }
//    private JAXBElement createJaxbElement(FormulaResponse1 object, Class clazz) {
//
//        return new JAXBElement<>(new QName(NAMESPACE_URI, clazz.getSimpleName()), clazz, object);
//
//    }
}
