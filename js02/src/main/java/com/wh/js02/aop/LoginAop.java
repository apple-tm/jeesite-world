package com.wh.js02.aop;

import com.wh.js02.entity.ResultVo;
import com.wh.js02.util.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Order(5)
public class LoginAop {
    @Autowired
    private RedisUtil redisUtil;

//    && (!@annotation(com.wh.js02.annotation.Exclude)))
    @Pointcut("within(com.wh.js02.controller..*)&&!within(com.wh.js02.controller.LoginController)")
    public void loginPointcut(){}

    @Around("loginPointcut()")
    public Object loginAop(ProceedingJoinPoint joinPoint) throws Throwable{
        ResultVo resultVo = new ResultVo();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String token = request.getHeader("token");

        Object p = null;
        try {
            if (token != null){
                String tk = (String)redisUtil.getObject(token);
                if (tk == null){
                    resultVo.needLogin();
                        return resultVo;
                } else {
                    // 重新设置超时时间为30分钟
                    redisUtil.expire(token, 30L, TimeUnit.MINUTES);
                }

            } else {
                resultVo.needLogin();
                return resultVo;
            }

//            String t = (String)redisUtil.getObject("jf");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return joinPoint.proceed();
    }

}
