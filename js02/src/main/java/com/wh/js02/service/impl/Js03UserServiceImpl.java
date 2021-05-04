package com.wh.js02.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wh.js02.entity.Js03User;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.mapper.Js03DeptMapper;
import com.wh.js02.mapper.Js03RoleMapper;
import com.wh.js02.mapper.Js03UserMapper;
import com.wh.js02.req.PageDTO;
import com.wh.js02.service.Js03UserService;
import com.wh.js02.util.RedisUtil;
import com.wh.js02.vo.PageVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class Js03UserServiceImpl implements Js03UserService {

    @Autowired
    private Js03UserMapper js03UserMapper;
    @Autowired
    private Js03RoleMapper js03RoleMapper;
    @Autowired
    private Js03DeptMapper js03DeptMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public ResultVo userLogin(Js03User user) throws Exception {
        Js03User js03User = js03UserMapper.userLogin(user);
        ResultVo resultVo = new ResultVo();

        if (js03User != null){
            if (!user.getPassword().equals(js03User.getPassword())){
                resultVo.fail("密码错误");
            } else {
                String uuid = UUID.randomUUID().toString().replace("-","").toUpperCase();
                redisUtil.setObject(uuid, JSONObject.toJSONString(js03User),30L,TimeUnit.MINUTES);
                String js03User1 =  (String)redisUtil.getObject(uuid);
                Js03User js03User2 = JSONObject.parseObject(js03User1, Js03User.class);
                js03User.setToken(uuid);
                resultVo.setBody(js03User);
                resultVo.success();
            }
        } else {
            resultVo.fail("用户不存在，请注册");
        }
        return resultVo;
    }

    @Override
    public ResultVo<PageVo<List<Js03User>>> pageQuery(PageDTO pageDTO) {
        //分页    limit offset num
        int offset = (pageDTO.getPageNo() - 1) * pageDTO.getPageSize();
        int number = pageDTO.getPageSize();
        //当前页用户
        List<Js03User> userList = js03UserMapper.pageQuery(offset,number);
        //状态为1的正常用户数（总记录数）
        int totalUser = js03UserMapper.queryUserNum();
        //总页数
        int totalPageNum = totalUser % number == 0 ? totalUser / number : totalUser / number + 1;

        ResultVo resultVo = new ResultVo();
        PageVo<List<Js03User>> pageVo = new PageVo<>();
        pageVo.setPageNo(pageDTO.getPageNo());
        pageVo.setPageSize(pageDTO.getPageSize());
        pageVo.setTotal(totalUser);
        pageVo.setTotalPage(totalPageNum);
        pageVo.setListBody(userList);
        if (userList != null){
            resultVo.success();
            resultVo.setBody(pageVo);
        } else {
            resultVo.fail("请求失败");
        }
        return resultVo;
    }

    @Override
    public ResultVo insertUser(Js03User user) {
        int count = js03UserMapper.insert(user);
        ResultVo resultVo = new ResultVo();

        if (count == 1){
            resultVo.success();
        } else {
            resultVo.fail("添加用户失败");
        }

        return resultVo;
    }




    @Override
    public ResultVo updateStatus(Js03User user) {
        int count = js03UserMapper.updateStatus(user);
        ResultVo resultVo = new ResultVo();

        if (count == 1){
            resultVo.success();
        } else {
            resultVo.fail("删除用户失败");
        }

        return resultVo;
    }

    @Override
    public ResultVo updateUserById(Js03User user) {
        int count = js03UserMapper.updateByPrimaryKey(user);
        ResultVo resultVo = new ResultVo();

        if (count == 1){
            resultVo.success();
        } else {
            resultVo.fail("修改用户失败");
        }

        return resultVo;
    }

    @Override
    public ResultVo<PageVo<List<Js03User>>> fuzzyQuery(PageDTO pageDTO) {
        ResultVo resultVo = new ResultVo<>();
        PageVo<List<Js03User>> pageVo = new PageVo<>();

        int offset = (pageDTO.getPageNo()-1) * pageDTO.getPageSize();
        int pageSize = pageDTO.getPageSize();
        String searchKey = pageDTO.getSearchKey();
        List<Js03User> userList = js03UserMapper.fuzzyQuery(offset,pageSize,searchKey);
        if (userList != null){
            //总记录数，总页数
            int total = userList.size();
            int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
            pageVo.setTotal(total);
            pageVo.setTotalPage(totalPage);
            pageVo.setListBody(userList);
            resultVo.success();
            resultVo.setBody(pageVo);
        } else {
            resultVo.fail("模糊查询失败");
        }

        return resultVo;
    }

    @Override
    public ResultVo selectUserById(Js03User user) {
        return null;
    }


    @Override
    public ResultVo insertBatch(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        ResultVo resultVo = new ResultVo();
        try {
            //获取文件输入流
            InputStream inputStream = file.getInputStream();
            Workbook workbook = null;
            //先判断文件类型
            if (fileType.equals(".xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (fileType.equals(".xls")){
                workbook = new HSSFWorkbook(inputStream);
            } else if (!fileType.equals(".xlsx") || !fileType.equals(".xls")){
                throw new IOException("文件格式错误");
            }
            //用户集合
            List<Js03User> userList = new ArrayList<Js03User>();
            //第一页
            Sheet sheetAt = workbook.getSheetAt(0);
            //循环第一页，忽略第一行
            int i = 0;
            for (Row row:sheetAt){
                if (row.getRowNum() < 1 || i == 0){
                    i++;
                    continue;
                }
                Js03User js03User = new Js03User();
                //用手机号查询数据库是否有该用户 todo

                String userName = row.getCell(0).getStringCellValue();

                Cell cell1 = row.getCell(1);
                cell1.setCellType(CellType.STRING);
                String password = cell1.getStringCellValue();

//                String password = String.valueOf(row.getCell(1).getNumericCellValue());
                String userCode = row.getCell(2).getStringCellValue();
//                String phone = String.valueOf(row.getCell(3).getNumericCellValue());
                Cell cell3 = row.getCell(3);
                cell3.setCellType(CellType.STRING);
                String phone = cell3.getStringCellValue();
                int roleId = (int)row.getCell(4).getNumericCellValue();
//                String deptCode = String.valueOf(row.getCell(5).getNumericCellValue());
                Cell cell5 = row.getCell(5);
                cell5.setCellType(CellType.STRING);
                String deptCode = cell5.getStringCellValue();
//                String parentDC = String.valueOf(row.getCell(6).getNumericCellValue());
                Cell cell6 = row.getCell(6);
                cell6.setCellType(CellType.STRING);
                String parentDC = cell6.getStringCellValue();
                int status = 1;
                js03User.setUserName(userName);
                js03User.setPassword(password);
                js03User.setUserCode(userCode);
                js03User.setPhone(phone);
                js03User.setRoleId(roleId);
                js03User.setDeptCode(deptCode);
                js03User.setParentDeptCode(parentDC);
                js03User.setStatus(status);
                js03User.setCreateBy("李四");
                js03User.setCreateTime(new Date());
                js03User.setUpdateBy("张三");
                js03User.setUpdateTime(new Date());
                userList.add(js03User);
            }
            int count = js03UserMapper.insertBatch(userList);

            if (count == userList.size()) {
                resultVo.success();
            } else {
                resultVo.fail("批量插入用户失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultVo;
    }
}
