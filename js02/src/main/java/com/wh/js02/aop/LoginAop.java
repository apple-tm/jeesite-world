package com.wh.js02.aop;

import com.alibaba.fastjson.JSON;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.util.RedisUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Aspect
@Component
public class LoginAop {
    @Autowired
    private RedisUtil redisUtil;

    @Pointcut("within(com.wh.js02.controller..*) && (!@annotation(com.wh.js02.annotation.Exclude)))")
    public void loginPointcut(){}

    @Around("loginPointcut()")
    public Object loginAop(ProceedingJoinPoint pjp) throws Throwable{
        ResultVo resultVo = new ResultVo();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String token = request.getHeader("token");
        Object p = null;
        try {
            if (token != null){
                String tk = (String)redisUtil.getObject(token);
                if (tk == null){
                    resultVo.fail("请登录再说");
                    return JSON.toJSONString(resultVo);
                }
            }

            p = pjp.proceed();

//            String t = (String)redisUtil.getObject("jf");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
        }

}
