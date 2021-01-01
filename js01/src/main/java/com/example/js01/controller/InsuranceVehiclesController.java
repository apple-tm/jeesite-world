package com.example.js01.controller;

import com.example.js01.entity.InsuranceVehicles;
import com.example.js01.service.InsuranceVehiclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InsuranceVehiclesController {

    @Autowired
    private InsuranceVehiclesService insuranceVehiclesService;
    @ResponseBody
    @RequestMapping("/addInsuranceVehicles")
    public String addInsuranceVehicles(@RequestBody InsuranceVehicles insuranceVehicles){
        return insuranceVehiclesService.addInsuranceVehicles(insuranceVehicles);
    }
}
