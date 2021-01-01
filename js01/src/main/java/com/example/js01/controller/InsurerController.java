package com.example.js01.controller;

import com.example.js01.entity.InsuranceVehicles;
import com.example.js01.entity.Insurer;
import com.example.js01.service.InsurerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InsurerController {
    @Autowired
    private InsurerService insurerService;

    @ResponseBody
    @RequestMapping(value = "/addInsurer")
    public String addInsurer(@RequestBody Insurer insurer){
        return insurerService.addInsurer(insurer);
    }
}
