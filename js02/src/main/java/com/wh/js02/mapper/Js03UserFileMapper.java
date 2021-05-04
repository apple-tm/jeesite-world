package com.wh.js02.mapper;

import com.wh.js02.entity.Js03UserFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Js03UserFileMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(Js03UserFile record);

    int insertSelective(Js03UserFile record);

    Js03UserFile selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(Js03UserFile record);

    int updateByPrimaryKey(Js03UserFile record);

    List<Js03UserFile> pageQuery(@Param("offset") int offset, @Param("number") int number,@Param("userId") int userId);
    //总记录数
    int queryUserFileNum();

    int updatePicture(Js03UserFile js03UserFile);

    int deletePicture(Integer id);

    List<Js03UserFile> fuzzyQuery(@Param("offset") int offset, @Param("number") int number, @Param("userId") int userId, @Param("searchKey") String searchKey);

    //模糊查询总记录
    int fuzzyQueryFileNum(String searchKey);

}