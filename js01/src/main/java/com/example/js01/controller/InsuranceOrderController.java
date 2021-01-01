package com.example.js01.controller;

import com.example.js01.entity.InsuranceOrder;
import com.example.js01.service.InsuranceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InsuranceOrderController {

    @Autowired
    private InsuranceOrderService insuranceOrderService;

    @RequestMapping(value = "/addInsuranceOrder")
    private String addInsuranceOrder(@RequestBody  InsuranceOrder insuranceOrder){
        return insuranceOrderService.addInsuranceOrder(insuranceOrder);
    }
}
