package com.example.dynamiccalculation.controller;

import com.concretepage.gs_ws.CreateFormulaRequest1;
import com.concretepage.gs_ws.FormulaResponse1;
import com.example.dynamiccalculation.data.FormulaRepository;
import com.example.dynamiccalculation.service.DynamicCalculationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

@Endpoint
public class FormulaEndpoint {
    private static final String NAMESPACE_URI = "http://www.baeldung.com/springsoap/gen";
    private DynamicCalculationServiceImpl calculatorService;

    @Autowired
    public FormulaEndpoint(DynamicCalculationServiceImpl calculatorService) {
        this.calculatorService = calculatorService;
    }

//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateFormulaRequest1")
//    @ResponsePayload
//    public FormulaResponse1 createFormulaRequest1(@RequestPayload CreateFormulaRequest1 request) {
//        FormulaResponse1 response = new FormulaResponse1();
//        calculatorService.createFormula(request.getFormula());
//        return response;
//    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createFormulaRequest1")
    @ResponsePayload
    public JAXBElement<FormulaResponse1> createFormulaRequest1(@RequestPayload JAXBElement<CreateFormulaRequest1> request) {
        FormulaResponse1 response = new FormulaResponse1();
//        calculatorService.createFormula(request.getFormula());
        return createJaxbElement(response, FormulaResponse1.class);
    }
    private JAXBElement createJaxbElement(FormulaResponse1 object, Class clazz) {

        return new JAXBElement<>(new QName(NAMESPACE_URI, clazz.getSimpleName()), clazz, object);

    }
}
