package com.wh.js02.Timing;

import com.wh.js02.mapper.Js02GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskAlarm {
    @Autowired
    private Js02GoodsMapper js02GoodsMapper;

    @Scheduled(cron = "0 45 19 ? * *" )
    public void goodsSumAlart(){
        js02GoodsMapper.goodsSumAlart();
    }

    @Scheduled(cron = "0 45 19 ? * *" )
    public void goodsSumAlart1(){
        js02GoodsMapper.goodsSumAlart1();
    }
}
