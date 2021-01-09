package com.example.js01.mian_shi_ti;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Test5 {
    char[] charArr1 = {'a','b','c'};
    char[] charArr2 = new char[]{'j','a','v','a'};
    char[] charArr3 = new char[5];
    static Character s1 = '吴';
    static String s2 = "吴";
    static String s3 = new String("吴");
    static String s4 = new String("吴");

    static String s5 = "asdffadsff";

    public static void main(String[] args) {
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s3.hashCode());
        System.out.println(s4.hashCode());

        System.out.println(s5.isEmpty());
        System.out.println(s5.length());
        System.out.println(s5.charAt(0));
        System.out.println(s5.substring(2,3));
        ;

    }
}
