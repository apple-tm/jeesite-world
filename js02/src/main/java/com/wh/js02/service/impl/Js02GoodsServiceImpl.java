package com.wh.js02.service.impl;

import com.wh.js02.entity.Js02Goods;
import com.wh.js02.mapper.Js02GoodsMapper;
import com.wh.js02.service.Js02GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Js02GoodsServiceImpl implements Js02GoodsService{
    @Autowired
    private Js02GoodsMapper js02GoodsMapper;

    @Override
    public int batchInsertGoods(List<Js02Goods> js02GoodsList) {
        return js02GoodsMapper.batchInsertGoods(js02GoodsList);
    }

    @Override
    public int selectGoods(String goodsName) {
        return js02GoodsMapper.selectGoods(goodsName);
    }

    @Override
    public int addGoodsSum(Js02Goods js02Goods) {
        return js02GoodsMapper.addGoodsSum(js02Goods);
    }



    @Override
    @Transactional
    public int transaction() {
        Js02Goods goods = new Js02Goods();
        goods.setJs02GoodsName("洗衣粉");
        goods.setJs02GoodsSum(20);
        int a = js02GoodsMapper.addGoodsSum(goods);
        int b = js02GoodsMapper.addGoodsSum(goods);
        int c = js02GoodsMapper.addGoodsSum(goods);
        int i = 1/0;
        return a+b+c;
    }

    @Async
    @Override
    public void asyncTest() {
        System.out.println(Thread.currentThread().getName());
        try{
            Thread.sleep(60*1000L);
        }catch (Exception e){
            System.out.println("执行fail");
        }finally {
            System.out.println("执行完成");
        }
    }
}
