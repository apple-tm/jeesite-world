package com.example.js01.mapper;

import com.example.js01.entity.RegisterUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {

    /**
     * 测试插入一个
     * @param registerUser
     * @return
     */
    Integer insertOne(RegisterUser registerUser);

    /**
     * 测试插入多个
     * @param registerUserList
     * @return
     */
    Integer insertBatch(List<RegisterUser> registerUserList);

    /**
     * 测试删除一个
     * @param id
     * @return
     */
    Integer deleteOne(Integer id);

    /**
     * 测试删除多个
     * @param idList
     * @return
     */
    Integer deleteBatch(List<RegisterUser> idList);

    /**
     * 测试修改一个
     * @param registerUser
     * @return
     */
    Integer updateOne(RegisterUser registerUser);

    // 修改多个 todo

    /**
     * 测试查询一个（注意查询条件的唯一性）或多个（动态 SQL）
     * 1.使用 phone 查询用户信息
     * 2.登录使用 phone + password
     * 3.limit 支持分页查询（动态 SQL）
     *
     * @param registerUser
     * @return
     */
    List<RegisterUser> selectRegisterUser(RegisterUser registerUser);

}
