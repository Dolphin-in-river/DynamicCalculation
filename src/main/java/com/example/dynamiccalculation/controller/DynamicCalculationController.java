//package com.example.dynamiccalculation.controller;
//
//import com.example.dynamiccalculation.dto.CreateFormulaRequest;
//import com.example.dynamiccalculation.dto.FormulaResponse;
//import com.example.dynamiccalculation.service.DynamicCalculationServiceImpl;
//import com.example.dynamiccalculation.service.DynamicCalculationServiceImpl;
//import jakarta.validation.Valid;
//import jakarta.validation.constraints.Min;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//@Validated
//@RestController
//public class DynamicCalculationController {
//    @Autowired
//    private DynamicCalculationServiceImpl calculatorService;
//    @PostMapping("/formul")
//    public ResponseEntity<?> createFormula(@Valid @RequestBody CreateFormulaRequest request) {
//        try {
//            System.out.println(request.getFormula());
//            return ResponseEntity.ok(calculatorService.createFormula(request.getFormula()));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
//
//    @GetMapping("/formul/{id}")
//    public ResponseEntity<?> getFormula(@PathVariable @Min(0) int id) {
//        try {
//            FormulaResponse response = calculatorService.getFormula(id);
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
//
//    @GetMapping("/geta")
//    public String getA() {
//        System.out.println("Gell");
//        return "Gell";
//    }
//}
