package com.example.js01.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.js01.constant.RedisConstant;
import com.example.js01.entity.*;
import com.example.js01.mapper.*;
import com.example.js01.service.InsuranceOrderService;
import com.example.js01.util.RedisUtil;
import org.omg.CORBA.INTERNAL;
import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class InsuranceOrderServiceImpl implements InsuranceOrderService {

    @Autowired
    private InsuranceOrderMapper insuranceOrderMapper;

    @Autowired
    private TypesOfInsuranceMapper typesOfInsuranceMapper;

    @Autowired
    private InsurerMapper insurerMapper;

    @Autowired
    private TheInsuredMapper theInsuredMapper;

    @Autowired
    private InsuranceVehiclesMapper insuranceVehiclesMapper;

    @Autowired
    private RedisUtil redisUtil;



    @Override
    // 测试事务作用 todo
    @Transactional
    public String addInsuranceOrder(InsuranceOrder insuranceOrder) {
        // 1.插入订单 返回订单ID
        //  1.1 查询保险信息，计算保费
        //  1.2 组装险种 ID 集合
        List<Integer> idList = new ArrayList<>(insuranceOrder.getTypesOfInsurance().size());
        List<TypesOfInsurance> typesOfInsuranceList = insuranceOrder.getTypesOfInsurance();
        for (TypesOfInsurance item3 : typesOfInsuranceList) {
            idList.add(item3.getId());
        }
        List<TypesOfInsurance> typesOfInsuranceList1 = typesOfInsuranceMapper.selectByIn(idList);
        // 1.3计算保费
        BigDecimal feeSum = new BigDecimal("0");
        for (TypesOfInsurance item4 : typesOfInsuranceList1) {
            BigDecimal curFee = new BigDecimal(item4.getInsurancePremium()).setScale(2, BigDecimal.ROUND_HALF_UP);
            feeSum = feeSum.add(curFee);
        }
//        insuranceOrder.setFeeSum(feeSum);
        Integer insureCount2 = insuranceOrderMapper.addInsuranceOrder(insuranceOrder);
        if (!insureCount2.equals(1)) {
            return "插入order信息错误";
        }
        // 2.插入车辆信息
        List<InsuranceOrderToCar> insuranceOrderToCarList = new ArrayList<>(insuranceOrder.getInsuranceVehicles().size());
        List<InsuranceVehicles> insuranceVehiclesList = insuranceOrder.getInsuranceVehicles();
        for (InsuranceVehicles item1 : insuranceVehiclesList) {
            InsuranceOrderToCar insuranceOrderToCar = new InsuranceOrderToCar();
            insuranceOrderToCar.setCarId(item1.getId());
            // 订单 ID 返回之后插入
            insuranceOrderToCar.setOrderId(insuranceOrder.getId());
            insuranceOrderToCarList.add(insuranceOrderToCar);
        }
        Integer carCount = insuranceOrderMapper.addInsuranceOrderToCar(insuranceOrderToCarList);
        if (!carCount.equals(insuranceOrder.getInsuranceVehicles().size())) {
            return "插入车辆信息错误";
        }
        // 3.插入保险列表
        List<InsuranceOrderToRisk> insuranceOrderToRiskList = new ArrayList<>(insuranceOrder.getTypesOfInsurance().size());
        for (TypesOfInsurance item2 : typesOfInsuranceList) {
            InsuranceOrderToRisk insuranceOrderToRisk = new InsuranceOrderToRisk();
            insuranceOrderToRisk.setInsureId(item2.getId());
            // 订单 ID 返回之后插入
            insuranceOrderToRisk.setOrderId(insuranceOrder.getId());
            insuranceOrderToRiskList.add(insuranceOrderToRisk);
        }
        Integer insureCount = insuranceOrderMapper.addInsuranceOrderToRisk(insuranceOrderToRiskList);
        if (!carCount.equals(insuranceOrder.getInsuranceVehicles().size())) {
            return "插入白保险信息错误";
        }

        return "order success";
    }


    @Override
    public InsuranceOrder queryOrder(InsuranceOrder insuranceOrder) {
        String key = new StringBuilder().append(RedisConstant.ORDER_PREFIX)
                .append(insuranceOrder.getId()).toString();
        InsuranceOrder order = null;
        try {
            redisUtil.setObject("wuhai",JSON.toJSONString("wuhai"),1L,TimeUnit.MINUTES);
            Object test = redisUtil.getObject("wuhai");
            Object insuranceOrderFromRedis = redisUtil.getObject(key);
            if (insuranceOrderFromRedis == null) {
                order = insuranceOrderMapper.queryOrder(insuranceOrder);
                if (order != null) {
                    redisUtil.setObject(key, JSON.toJSONString(order), 1L, TimeUnit.MICROSECONDS);
                }
            } else {
                order =JSON.parseObject((String)insuranceOrderFromRedis, InsuranceOrder.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return order;
        }
    }

    @Override
    public List<InsuranceOrder> rangQuery(QueryCommon queryCommon) {

        return insuranceOrderMapper.rangQuery(queryCommon);
    }

    @Override
    public InsuranceOrder queryorder(InsuranceOrder insuranceOrder) {
        Integer orderId = insuranceOrder.getId();
        InsuranceOrder order = insuranceOrderMapper.queryorder(insuranceOrder);
        //通过order表的保险公司id查询出保险公司信息
        Integer insuredId = order.getInsuredId();
        Insurer insurer = insurerMapper.queryInsurer(insuredId);
        InsuranceOrder insuranceOrder1 = new InsuranceOrder();
        insuranceOrder1.setInsurer(insurer);

        //通过order表的被保险人id查询出被保险人信息
        Integer theInsuredId = order.getTheInsuredId();
        TheInsured theInsured = theInsuredMapper.queryTheInsured(theInsuredId);
        insuranceOrder1.setTheInsured(theInsured);

        //通过订单id以中间表关联车表查询出多辆车信息
        List<InsuranceVehicles> insuranceVehiclesList = insuranceVehiclesMapper.queryInsuranceVehicles(orderId);
        insuranceOrder1.setInsuranceVehicles(insuranceVehiclesList);

        //通过订单id以中间表关联险种表查询出多个险种信息
        List<TypesOfInsurance> typesOfInsuranceList = typesOfInsuranceMapper.queryInsuranceType(orderId);
        insuranceOrder1.setTypesOfInsurance(typesOfInsuranceList);



        return insuranceOrder1;
    }

    @Override
    public List<InsuranceOrder> queryOrderByTheInsuredName(TheInsured theInsured1) {
        List<InsuranceOrder> orders = insuranceOrderMapper.queryOrderByTheInsuredName(theInsured1);
        List<InsuranceOrder> orderList = new ArrayList<>();
        for(InsuranceOrder order:orders) {
            Integer orderId = order.getId();
            //通过order表的保险公司id查询出保险公司信息
            Integer insuredId = order.getInsuredId();
            Insurer insurer = insurerMapper.queryInsurer(insuredId);
            InsuranceOrder insuranceOrder2 = new InsuranceOrder();
            insuranceOrder2.setInsurer(insurer);

            //通过order表的被保险人id查询出被保险人信息
            Integer theInsuredId = order.getTheInsuredId();
            TheInsured theInsured = theInsuredMapper.queryTheInsured(theInsuredId);
            insuranceOrder2.setTheInsured(theInsured);

            //通过订单id以中间表关联车表查询出多辆车信息
            List<InsuranceVehicles> insuranceVehiclesList = insuranceVehiclesMapper.queryInsuranceVehicles(orderId);
            insuranceOrder2.setInsuranceVehicles(insuranceVehiclesList);

            //通过订单id以中间表关联险种表查询出多个险种信息
            List<TypesOfInsurance> typesOfInsuranceList = typesOfInsuranceMapper.queryInsuranceType(orderId);
            insuranceOrder2.setTypesOfInsurance(typesOfInsuranceList);

            orderList.add(insuranceOrder2);
        }
        return orderList;
    }

    @Override
    public List<InsuranceOrder> queryOrderByInsurerName(Insurer insurer1) {
        List<InsuranceOrder> orderList = new ArrayList<>();
        List<InsuranceOrder> orders = insuranceOrderMapper.queryOrderByInsurerName(insurer1);
        for (InsuranceOrder order: orders) {
            Integer orderId = order.getId();
            //通过order表的保险公司id查询出保险公司信息
            Integer insuredId = order.getInsuredId();
            Insurer insurer = insurerMapper.queryInsurer(insuredId);
            InsuranceOrder insuranceOrder3 = new InsuranceOrder();
            insuranceOrder3.setInsurer(insurer);

            //通过order表的被保险人id查询出被保险人信息
            Integer theInsuredId = order.getTheInsuredId();
            TheInsured theInsured = theInsuredMapper.queryTheInsured(theInsuredId);
            insuranceOrder3.setTheInsured(theInsured);

            //通过订单id以中间表关联车表查询出多辆车信息
            List<InsuranceVehicles> insuranceVehiclesList = insuranceVehiclesMapper.queryInsuranceVehicles(orderId);
            insuranceOrder3.setInsuranceVehicles(insuranceVehiclesList);

            //通过订单id以中间表关联险种表查询出多个险种信息
            List<TypesOfInsurance> typesOfInsuranceList = typesOfInsuranceMapper.queryInsuranceType(orderId);
            insuranceOrder3.setTypesOfInsurance(typesOfInsuranceList);
            orderList.add(insuranceOrder3);
        }
        return orderList;
    }


    @Override
    public List<InsuranceOrder> queryOrderByplateNumber(InsuranceVehicles insuranceVehicles) {
        List<InsuranceOrder> orders = insuranceOrderMapper.queryOrderByplateNumber(insuranceVehicles);

        List<InsuranceOrder> orderList = new ArrayList<>();
        for(InsuranceOrder order:orders){
            Integer orderId = order.getId();
            //通过order表的保险公司id查询出保险公司信息
            Integer insuredId = order.getInsuredId();
            Insurer insurer = insurerMapper.queryInsurer(insuredId);
            InsuranceOrder insuranceOrder4 = new InsuranceOrder();
            insuranceOrder4.setInsurer(insurer);

            //通过order表的被保险人id查询出被保险人信息
            Integer theInsuredId = order.getTheInsuredId();
            TheInsured theInsured = theInsuredMapper.queryTheInsured(theInsuredId);
            insuranceOrder4.setTheInsured(theInsured);

            //通过订单id以中间表关联车表查询出多辆车信息
            List<InsuranceVehicles> insuranceVehiclesList = insuranceVehiclesMapper.queryInsuranceVehicles(orderId);
            insuranceOrder4.setInsuranceVehicles(insuranceVehiclesList);

            //通过订单id以中间表关联险种表查询出多个险种信息
            List<TypesOfInsurance> typesOfInsuranceList = typesOfInsuranceMapper.queryInsuranceType(orderId);
            insuranceOrder4.setTypesOfInsurance(typesOfInsuranceList);


            orderList.add(insuranceOrder4);
        }


        return orderList;
    }
}
