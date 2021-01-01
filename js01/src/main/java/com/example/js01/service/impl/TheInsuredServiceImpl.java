package com.example.js01.service.impl;

import com.example.js01.entity.TheInsured;
import com.example.js01.mapper.TheInsuredMapper;
import com.example.js01.service.TheInsuredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheInsuredServiceImpl implements TheInsuredService{
    @Autowired
    private TheInsuredMapper theINsuredMapper;
    @Override
    public String addTheInsured(TheInsured theInsured) {
        return theINsuredMapper.addTheInsured(theInsured) == 1 ? "添加被保险人成功" : "添加被保险人失败了";
    }
}
