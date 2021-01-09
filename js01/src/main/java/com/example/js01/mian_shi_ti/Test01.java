package com.example.js01.mian_shi_ti;

//使用 final 关键字修饰一个变量时，是引用不能变，还是引用的对象不能变？
public class Test01 {
    public final static char CHAR = '中';
    public final static StringBuilder strB = new StringBuilder("中");


    //基本数据类型(变量里存的是具体的值，final修饰后，即为常量，不可改变其值)
    /*public static void method1(final int i){
        i += 1;
    }*/

    //引用数据类型（final修饰后，引用变量不能变，但引用变量指向的对象的内容是可以变的）
    /*public static void method2(final StringBuilder strB){
        strB.append("中国");
    }*/

    public static void main(String[] args) {
//        strB = new StringBuilder();
        strB.append("国");
        System.out.println(strB);
    }
}
