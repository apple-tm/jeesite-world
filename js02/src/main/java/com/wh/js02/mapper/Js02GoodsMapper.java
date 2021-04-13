package com.wh.js02.mapper;

import com.wh.js02.entity.Js02Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
@Mapper
public interface Js02GoodsMapper {
    int deleteByPrimaryKey(Integer js02GoodsId);

    int insert(Js02Goods record);

    int insertSelective(Js02Goods record);

    Js02Goods selectByPrimaryKey(Integer js02GoodsId);

    int updateByPrimaryKeySelective(Js02Goods record);

    int updateByPrimaryKey(Js02Goods record);


    int batchInsertGoods(List<Js02Goods> js02GoodsList);

    int selectGoods(String goodsName);

    int addGoodsSum(Js02Goods js02Goods);

    void goodsSumAlart();

    void goodsSumAlart1();


}