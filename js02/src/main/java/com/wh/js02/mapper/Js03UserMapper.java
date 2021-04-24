package com.wh.js02.mapper;

import com.wh.js02.entity.Js03User;
import com.wh.js02.req.PageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface Js03UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Js03User record);

    Js03User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Js03User record);

    int updateByPrimaryKey(Js03User record);

    /**
     * 新增用户
     * @param record
     * @return
     */
    int insert(Js03User record);

    /**
     * 用户登录，使用手机号查询用户
     * @param user
     * @return
     */
    Js03User userLogin(Js03User user);

    /**
     * 分页查询
     * @param offset
     * @param number
     * @return
     */
    List<Js03User> pageQuery(@Param("offset") int offset,@Param("number") int number);

    /**
     * 查询正常用户数量
     * @return
     */
    int queryUserNum();

    /**
     * 删除用户，将状态改为-1
     * @param user
     * @return
     */
    int updateStatus(Js03User user);

    /**
     * 模糊分页查询
     * @param offset
     * @param pageSize
     * @param searchKey
     * @return
     */
    List<Js03User> fuzzyQuery(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize,@Param("searchKey") String searchKey);
}