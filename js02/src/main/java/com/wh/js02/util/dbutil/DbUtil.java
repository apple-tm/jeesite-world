//package com.wh.js02.util.dbutil;
//
//import java.sql.*;
//
//public class DbUtil {
//    public static final String URL = "jdbc:mysql://localhost:3306/js02?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";
//    public static final String USER = "root";
//    public static final String PASSWORD = "root";
//    //这个是新版本的数据库驱动名字，com.mysql.driver是老版本
//    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
//    public static void main(String[] args) throws Exception{
//        String querysql = "select * from user_entity";
//        //1. 加载驱动
//        Class.forName(DRIVER);
//        //2. 获取数据库连接
//        Connection cn = DriverManager.getConnection(URL,USER,PASSWORD);
//        //3. 通过连接创建statement对象
//        Statement statement = cn.createStatement();
//        //4. 执行sql
//        ResultSet resultSet = statement.executeQuery(querysql);
//        //5. 迭代
//        while(resultSet.next()){
//            System.out.println("手机号：" + resultSet.getString("phone_number") + "密码：" + resultSet.getString("user_password"));
//        }
//
//    }
//}
