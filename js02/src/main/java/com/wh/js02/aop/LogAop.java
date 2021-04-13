package com.wh.js02.aop;

import com.wh.js02.entity.Js02Log;
import com.wh.js02.mapper.Js02LogMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

//@Aspect
//@Component
public class LogAop {

    @Autowired
    private Js02LogMapper js02LogMapper;

    @Pointcut("execution(public * com.wh.js02.controller..*.*(..))")//切入点描述 这个是controller包的切入点
    public void controllerLog() {
    }//签名，可以理解成这个切入点的一个名称

    @Before("controllerLog()") //在切入点的方法run之前要干的
    public void logBeforeController(JoinPoint joinPoint) {
        //获取用户id
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();//这个RequestContextHolder是Springmvc提供来获得请求的东西
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String sr = request.getRequestURL().toString();

        //获取用户权限列表
        //遍历角色列表
        request.getHeader("userId");
        Js02Log js02Log = new Js02Log();
        js02Log.setJs02LogMethod(request.getMethod());
        js02Log.setJs02LogTime(new Date());
        js02Log.setJs02LogUrl(request.getRequestURL().toString());
        js02Log.setJs02UserId(2);
        js02LogMapper.insert(js02Log);

    }
}
