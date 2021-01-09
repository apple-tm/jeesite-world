package com.example.js01.controller;

import com.example.js01.entity.*;
import com.example.js01.service.InsuranceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private InsuranceOrderService insuranceOrderService;

    /**
     * 添加订单
     * @param insuranceOrder
     * @return
     */
    @RequestMapping(value = "/addInsuranceOrder")
    @ResponseBody
    private String addInsuranceOrder(@RequestBody  InsuranceOrder insuranceOrder){
        return insuranceOrderService.addInsuranceOrder(insuranceOrder);
    }

    @RequestMapping(value = "/addManyInsuranceOrder")
    @ResponseBody
    private String addManyInsuranceOrder(@RequestBody  InsuranceOrder insuranceOrder){
        int sum = 0;
        for (int i=0; i<200000; i++) {
            insuranceOrder.setFeeSum(new BigDecimal(i));
            insuranceOrder.setStatus(i%2==0 ? "1" : "0");
            insuranceOrder.setCreatedBy(new StringBuilder().append("管理员").append(i).toString());
            String count = insuranceOrderService.addInsuranceOrder(insuranceOrder);
            if (count.equals("order success")) {
                sum++;
            }
        }
        return String.valueOf(sum);
    }

    @ResponseBody
    @RequestMapping(value = "/queryOrder")
    public InsuranceOrder queryOrder(@RequestBody InsuranceOrder insuranceOrder){
        long startTime = System.currentTimeMillis();
        InsuranceOrder insuranceOrder1 = insuranceOrderService.queryOrder(insuranceOrder);
        System.out.println(System.currentTimeMillis() - startTime);
        return insuranceOrder1;
    }

    @GetMapping("/rangeQuery")
    @ResponseBody
    public List<InsuranceOrder> rangeQuery(@RequestBody QueryCommon queryCommon){

        return insuranceOrderService.rangQuery(queryCommon);
    }

    @GetMapping("/queryorder")
    @ResponseBody
    public InsuranceOrder queryorder(@RequestBody InsuranceOrder insuranceOrder){
        return insuranceOrderService.queryorder(insuranceOrder);
    }

    @GetMapping("/queryOrderByTheInsuredName")
    @ResponseBody
    public List<InsuranceOrder> queryOrderByTheInsuredName(@RequestBody TheInsured theInsured){
        return insuranceOrderService.queryOrderByTheInsuredName(theInsured);
    }

    @GetMapping("/queryOrderByInsurerName")
    @ResponseBody
    public List<InsuranceOrder> queryOrderByInsurerName(@RequestBody Insurer insurer){
        return insuranceOrderService.queryOrderByInsurerName(insurer);
    }

    @GetMapping("/queryOrderByplateNumber")
    @ResponseBody
    public List<InsuranceOrder> queryOrderByplateNumber(@RequestBody InsuranceVehicles insuranceVehicles){
        return insuranceOrderService.queryOrderByplateNumber(insuranceVehicles);
    }
}
