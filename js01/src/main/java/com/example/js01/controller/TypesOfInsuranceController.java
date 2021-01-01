package com.example.js01.controller;

import com.example.js01.entity.TypesOfInsurance;
import com.example.js01.service.TypesOfInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TypesOfInsuranceController {

    @Autowired
    private TypesOfInsuranceService insuranceService;


    @ResponseBody
    @RequestMapping(value ="/addInsurance")
    public String addInsurance(@RequestBody TypesOfInsurance insurance){

        return insuranceService.addInsurance(insurance);
    }



}
