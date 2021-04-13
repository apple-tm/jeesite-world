package com.wh.js02.service;

import com.wh.js02.entity.Js02Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface Js02GoodsService {
    int batchInsertGoods(List<Js02Goods> js02GoodsList);
    int selectGoods(String goodsName);
    int addGoodsSum(Js02Goods js02Goods);

    int transaction();

    void asyncTest();
}
