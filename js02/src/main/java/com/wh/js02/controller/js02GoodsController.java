package com.wh.js02.controller;

import com.wh.js02.entity.Js02Goods;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.entity.Test;
import com.wh.js02.service.Js02GoodsService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

@RestController
public class js02GoodsController {
    @Autowired
    private Js02GoodsService js02GoodsService;

    @PostMapping(value = "/batchInsertGoods")
    public ResultVo batchInsert(MultipartFile file) throws Exception {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        //获取行数
        int lastRowNum = sheet.getLastRowNum();
        List<Js02Goods> js02GoodsList = new ArrayList<Js02Goods>();
        for (int i = 0; i < lastRowNum+1; i++) {

            Js02Goods js02Goods = new Js02Goods();

            XSSFRow row = sheet.getRow(i);
            String goodsName = row.getCell(0).getStringCellValue();
            long goodsPrice = (long)row.getCell(1).getNumericCellValue();
            String goodsType = row.getCell(2).getStringCellValue();
            int goodsSum = (int)row.getCell(3).getNumericCellValue();

            js02Goods.setJs02GoodsName(goodsName);
            js02Goods.setJs02GoodsPrice(goodsPrice);
            js02Goods.setJs02GoodsType(goodsType);
            js02Goods.setJs02GoodsSum(goodsSum);
            //批量插入前查询是否存在该商品
            int isExist = js02GoodsService.selectGoods(js02Goods.getJs02GoodsName());
            //如果存在，就修改库存量字段
            if (isExist == 1){
                js02GoodsService.addGoodsSum(js02Goods);
                System.out.println("增加了"+js02Goods.getJs02GoodsName()+"的库存量"+js02Goods.getJs02GoodsSum());

            }
            //不存在，将该商品对象添加到集合中
            if (isExist == 0){
                js02GoodsList.add(js02Goods);
                if (js02GoodsList.size() != 0){
                    js02GoodsService.batchInsertGoods(js02GoodsList);
                }
            }
        }
        ResultVo resultVo = new ResultVo();
            resultVo.success();
            return resultVo;
    }

    @PostMapping(value = "/testtransaction")
    public @ResponseBody
    String testController(@RequestBody Test test){
        int count = js02GoodsService.transaction();
        ResultVo resultVo = new ResultVo();
        if (count > 0){
            resultVo.success();
        }
        return resultVo.toString();
    }


    @PostMapping(value = "/testasync")
    public @ResponseBody
    String testasync(@RequestBody Test test){
        System.out.println(Thread.currentThread().getName());
        js02GoodsService.asyncTest();
        ResultVo resultVo = new ResultVo();

        return resultVo.toString();
    }
}
