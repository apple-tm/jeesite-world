package com.example.js01.service.impl;

import com.example.js01.entity.Insurer;
import com.example.js01.mapper.InsurerMapper;
import com.example.js01.service.InsurerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsurerServiceImpl implements InsurerService{
    @Autowired
    private InsurerMapper insurerMapper;
    @Override
    public String addInsurer(Insurer insurer) {
        return insurerMapper.addInsurer(insurer) == 1 ? "添加保险人成功" : "添加保险人失败";
    }
}
