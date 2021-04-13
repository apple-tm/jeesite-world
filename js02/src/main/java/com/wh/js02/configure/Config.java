package com.wh.js02.configure;

import com.wh.js02.util.RedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public RedisUtil redisUtil(){
        return new RedisUtil();
    }
}
