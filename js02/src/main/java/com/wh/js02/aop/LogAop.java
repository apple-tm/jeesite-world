package com.wh.js02.aop;

import com.alibaba.fastjson.JSON;
import com.wh.js02.entity.Js03Log;
import com.wh.js02.entity.Js03User;
import com.wh.js02.entity.ResultVo;
import com.wh.js02.mapper.Js02LogMapper;
import com.wh.js02.mapper.Js03LogMapper;
import com.wh.js02.mapper.Js03UserMapper;
import com.wh.js02.util.RedisUtil;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
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
import java.util.Date;
import java.util.StringTokenizer;

@Aspect
@Component
@Order(1)
public class LogAop {

    @Autowired
    private Js03LogMapper js03LogMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private Js03UserMapper js03UserMapper;

    @Pointcut("execution(public * com.wh.js02.controller..*.*(..))")//切入点描述 这个是controller包的切入点
    public void controllerLog() {
    }//签名，可以理解成这个切入点的一个名称

    @Around("controllerLog()") //在切入点的方法run之前要干的
    public ResultVo logBeforeController(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        ResultVo resultVo = new ResultVo();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String token = request.getHeader("token");

        String url = request.getRequestURL().toString();

        Js03Log js03Log = new Js03Log();

        Object p = null;
        try {
            //ip
            String logUserIp = request.getRemoteAddr();
            //请求的路径
            String contextPath = url.substring(url.lastIndexOf("/api"));
            //浏览器名称
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
            Browser browser = userAgent.getBrowser();
            String browserName = browser.getName();
            //操作系统名称
            OperatingSystem os = userAgent.getOperatingSystem();
            String osName = os.getName();
            //创建时间
            Date createTime = new Date();
            Date updateTime = createTime;

            js03Log.setLogUserIp(logUserIp);
            js03Log.setLogContextPath(contextPath);
            js03Log.setLogBName(browserName);
            js03Log.setLogOsName(osName);
            js03Log.setCreateTime(createTime);
            js03Log.setUpdateTime(updateTime);

            if (token != null){
                String tk = (String)redisUtil.getObject(token);
                if (tk == null){
                    // 请求超时
                    resultVo.needLogin();
                }
                //登录后用户操作记录
                else {
                    if (url.contains("/api/user/add")) {
                        js03Log.setLogAction("用户新增");
                    } else if (url.contains("/api/user/delete")) {
                        js03Log.setLogAction("用户删除");
                    } else if (url.contains("/api/user/update")) {
                        js03Log.setLogAction("用户修改");
                    } else if (url.contains("/api/user/query")) {
                        js03Log.setLogAction("用户查询");
                    } else if (url.contains("/api/user/pageQuery")) {
                        js03Log.setLogAction("用户分页查询");
                    } else if (url.contains("/api/user/fuzzyQuery")) {
                        js03Log.setLogAction("用户模糊查询");
                    } else if (url.contains("api/user/insertBatch")) {
                        js03Log.setLogAction("用户批量新增");
                    }
                    else if (url.contains("api/role/insert")) {
                        js03Log.setLogAction("角色新增");
                    } else if (url.contains("/api/role/delete")) {
                        js03Log.setLogAction("角色删除");
                    } else if (url.contains("/api/role/update")) {
                        js03Log.setLogAction("角色修改");
                    } else if (url.contains("/api/role/query")) {
                        js03Log.setLogAction("角色查询");
                    } else if (url.contains("api/role/fuzzy")) {
                        js03Log.setLogAction("角色模糊查询");
                    }
                    else if (url.contains("api/dept/insert")) {
                        js03Log.setLogAction("机构新增");
                    } else if (url.contains("/api/dept/delete")) {
                        js03Log.setLogAction("机构删除");
                    } else if (url.contains("/api/dept/update")) {
                        js03Log.setLogAction("机构修改");
                    } else if (url.contains("/api/dept/query")) {
                        js03Log.setLogAction("机构查询");
                    } else if (url.contains("api/dept/fuzzyQuery")) {
                        js03Log.setLogAction("机构模糊查询");
                    }
                    else if (url.contains("api/menu/insert")) {
                        js03Log.setLogAction("菜单新增");
                    } else if (url.contains("/api/menu/delete")) {
                        js03Log.setLogAction("菜单删除");
                    } else if (url.contains("/api/menu/update")) {
                        js03Log.setLogAction("菜单修改");
                    } else if (url.contains("/api/menu/select")) {
                        js03Log.setLogAction("菜单查询");
                    } else if (url.contains("/api/menu/fuzzy")) {
                        js03Log.setLogAction("菜单模糊查询");
                    } else if (url.contains("/api/file/pageQuery")) {
                        js03Log.setLogAction("图片分页查询");
                    } else if (url.contains("/api/image/upload")) {
                        js03Log.setLogAction("图片上传");
                    } else if (url.contains("/api/image/update")) {
                        js03Log.setLogAction("图片修改");
                    } else if (url.contains("/api/image/delete")) {
                        js03Log.setLogAction("图片删除");
                    } else if (url.contains("/api/image/fuzzyQuery")) {
                        js03Log.setLogAction("图片模糊查询");
                    } else if (url.contains("/api/log/query")) {
                        js03Log.setLogAction("日志查询");
                    }

                    //当前登录用户
                    Js03User curUser = JSON.parseObject(tk,Js03User.class);
                    //用户id
                    int curUserId = curUser.getId();
                    //用户名
                    String userName = curUser.getUserName();
                    //用户角色id
                    int roleId = curUser.getRoleId();

                    String logUserRoleName = curUser.getJs03Role().getRoleName();
                    String createBy = userName;
                    String updateBy = userName;
                    js03Log.setLogUserId(curUserId);
                    js03Log.setLogUserName(userName);
                    js03Log.setLogUserRoleId(String.valueOf(roleId));
                    js03Log.setLogUserRoleName(logUserRoleName);
                    js03Log.setCreateBy(createBy);
                    js03Log.setUpdateBy(updateBy);
                }

            } else {
                //登录接口，token为空，不管登录是否成功
                //请求动作
                String logAction = "登录";


                js03Log.setLogAction(logAction);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取被切函数的返回值
        ResultVo returnValue = (ResultVo) joinPoint.proceed();
        //登录失败，原因：用户不存在
        if ("用户不存在，请注册".equals(returnValue.getRespMsg())) {
            String logNotes = "用户不存在";
            int userId = -1;
            String userName = "null";
            String userRoleId = "null";
            String logUserRoleName = "null";
            String createBy = "null";
            String updateBy = "null";

            js03Log.setLogNotes(logNotes);
            js03Log.setLogUserId(userId);
            js03Log.setLogUserName(userName);
            js03Log.setLogUserRoleId(userRoleId);
            js03Log.setLogUserRoleName(logUserRoleName);
            js03Log.setCreateBy(createBy);
            js03Log.setUpdateBy(updateBy);

        } else if ("密码错误".equals(returnValue.getRespMsg())) {
            //登录失败，原因：密码错误,用户是存在的
            //获取登录接口的参数值
            Object[] value = joinPoint.getArgs();
            Js03User js03User = (Js03User) value[0];

            //通过参数值，即手机号获取该用户信息
            Js03User user = js03UserMapper.userLogin(js03User);

            String logNotes = "密码错误";
            int userId = user.getId();
            String userName = user.getUserName();
            String userRoleId = String.valueOf(user.getRoleId());
            String logUserRoleName = user.getJs03Role().getRoleName();
            String createBy = user.getUserName();
            String updateBy = createBy;

            js03Log.setLogNotes(logNotes);
            js03Log.setLogUserId(userId);
            js03Log.setLogUserName(userName);
            js03Log.setLogUserRoleId(userRoleId);
            js03Log.setLogUserRoleName(logUserRoleName);
            js03Log.setCreateBy(createBy);
            js03Log.setUpdateBy(updateBy);
        }

        else if (returnValue.getBody() != null && Js03User.class.getName().equals(returnValue.getBody().getClass().getName())) {
            //登录成功
            //获取当前登录用户信息
            Js03User user = (Js03User) returnValue.getBody();

            String logNotes = "登录成功";
            int userId = user.getId();
            String userName = user.getUserName();
            String userRoleId = String.valueOf(user.getRoleId());
            String logUserRoleName = user.getJs03Role().getRoleName();
            String createBy = user.getUserName();
            String updateBy = createBy;

            js03Log.setLogNotes(logNotes);
            js03Log.setLogUserId(userId);
            js03Log.setLogUserName(userName);
            js03Log.setLogUserRoleId(userRoleId);
            js03Log.setLogUserRoleName(logUserRoleName);
            js03Log.setCreateBy(createBy);
            js03Log.setUpdateBy(updateBy);

        } else {
            //登录后，其他接口记录，从redis中获取登录用户信息
            String tk = (String)redisUtil.getObject(token);
            Js03User user = JSON.parseObject(tk,Js03User.class);

            int userId = user.getId();
            String userName = user.getUserName();
            String userRoleId = String.valueOf(user.getRoleId());
            String logUserRoleName = user.getJs03Role().getRoleName();
            String createBy = user.getUserName();
            String updateBy = createBy;

            js03Log.setLogUserId(userId);
            js03Log.setLogUserName(userName);
            js03Log.setLogUserRoleId(userRoleId);
            js03Log.setLogUserRoleName(logUserRoleName);
            js03Log.setCreateBy(createBy);
            js03Log.setUpdateBy(updateBy);
        }


        long end = System.currentTimeMillis();
        int runTime = (int) (end - start);

        js03Log.setLogRunTime(runTime);
        int count = js03LogMapper.insertLog(js03Log);

        if (count == 1) {
            resultVo.success();
        } else {
            resultVo.fail("日志记录失败");
        }

        return returnValue;

    }
}
