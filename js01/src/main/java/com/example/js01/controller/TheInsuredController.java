package com.example.js01.controller;

import com.example.js01.entity.TheInsured;
import com.example.js01.service.TheInsuredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 被保险人
 */
@Controller
public class TheInsuredController {
    @Autowired
    private TheInsuredService theInsuredService;

    /**
     * 添加被保险人
     * @param theInsured
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addTheInsured")
    public String addTheInsured(@RequestBody TheInsured theInsured){
        return theInsuredService.addTheInsured(theInsured);
    }
}
