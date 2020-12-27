package com.example.js01.service.impl;

import com.example.js01.entity.LoginReqDTO;
import com.example.js01.entity.RegisterUser;
import com.example.js01.mapper.TestMapper;
import com.example.js01.service.RegisterUserService;
import com.example.js01.util.Encryption;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.beans.Beans;
import java.util.List;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public List<RegisterUser> queryOneUser(String phone) throws Exception {
        RegisterUser registerUser;
        // 1.为什么要去掉前后空格，空格不是空字符串， null 代表没有在堆中创建对象
        // 2.null调用任何方法都会出现空指针，java.lang.NullPointerException
        // 3.包确定不为 null 的对象放在前面调用 equals 可以有效避免空指针异常
        if (phone != null && !"".equals(phone.trim())) {
            registerUser = new RegisterUser();
            registerUser.setPhone(phone);
            return testMapper.selectRegisterUser(registerUser);
        }  else {
            throw  new NullPointerException("phone 参数为空");
        }
    }

    @Override
    public Integer registerUser(RegisterUser registerUser) {
        registerUser.setPassword(Encryption.encryp(registerUser.getPassword()));
        registerUser.setName(new StringBuilder().append(registerUser.getRePassword()).append("_游客").toString());
        registerUser.setCreatedBy("超级管理员");
        registerUser.setUpdatedBy("超级管理员");
        return testMapper.insertOne(registerUser);
    }

    @Override
    public Integer registerManyUser(List<RegisterUser> registerUserList) {
        int phone = 1320251;
        int startItem = 1000;
        for (int i=0; i < 1000; i++) {
            RegisterUser registerUser = new RegisterUser();
            registerUser.setPassword(Encryption.encryp("123admin123!"));
            registerUser.setName(new StringBuilder().append("用户").append(i).toString());
            registerUser.setCreatedBy("超级管理员");
            registerUser.setUpdatedBy("超级管理员");
            String curPhone = new StringBuilder().append(phone).append(startItem).toString();
            registerUser.setPhone(curPhone);
            registerUserList.add(registerUser);
            startItem++;
        }

        return testMapper.insertBatch(registerUserList);
    }

    @Override
    public Integer deleteOneUser(RegisterUser registerUser) {
//        if (registerUser == null || registerUser.getId() == null) {
//            return 0;
//        } else {
//            return testMapper.deleteOne(registerUser.getId());
//        }
        return (registerUser == null || registerUser.getId() == null) ? 0 : testMapper.deleteOne(registerUser.getId());
    }

    @Override
    // 保证删除修改插入的一致性，就是要么同时成功，要么同时失败，保证事务正确性
    @Transactional
    public Integer deleteManyUser(List<RegisterUser> registerUserList) {
        return testMapper.deleteBatch(registerUserList);
    }

    @Override
    public Integer updateOneUser(RegisterUser registerUser) {
        // trim() 去掉首尾空格
        if (registerUser !=null
                && registerUser.getPassword() !=null
                && !registerUser.getPassword().trim().equals("")) {
            registerUser.setPassword(Encryption.encryp(registerUser.getPassword().trim()));
        }
        // 校验密码格式，电话，邮箱，状态...,这些可以前端校验
        return testMapper.updateOne(registerUser);
    }

    @Override
    public List<RegisterUser> login(LoginReqDTO registerUser) throws Exception {
        if (registerUser !=null
                && registerUser.getPhone() != null
                && !"".equals(registerUser.getPhone().trim())
                && registerUser.getPassword() != null
                && !"".equals(registerUser.getPassword().trim())) {
            registerUser.setPassword(Encryption.encryp(registerUser.getPassword()));
            RegisterUser user = new RegisterUser();
//            user.setPhone(registerUser.getPhone());
//            user.setPassword(Encryption.encryp(registerUser.getPassword()));

            // 从参数 1 复制相同属性名的属性值到参数 2
            BeanUtils.copyProperties(registerUser, user);
            return testMapper.selectRegisterUser(user);
        } else {
            throw new Exception("电话号码或密码为空");
        }
    }

    @Override
    public List<RegisterUser> pageQuery(RegisterUser registerUser) throws Exception {
        if (registerUser !=null
                && registerUser.getStartPage() != null
                && registerUser.getPageSize() != null) {
            // 前端传递 startPage = 1 后端匹配数据库 limit
            registerUser.setStartPage(registerUser.getStartPage()-1);
            return testMapper.selectRegisterUser(registerUser);
        } else {
            throw new Exception("分页参数为空");
        }
    }
}
