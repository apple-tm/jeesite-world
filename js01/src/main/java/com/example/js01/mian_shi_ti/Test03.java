package com.example.js01.mian_shi_ti;

import org.omg.CORBA.INTERNAL;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class Test03 {
    static int i1 = 100;
    static int i2 = 100;
    static Integer i3 = 100;
    static Integer i4 = new Integer(100);
    static char c = '一';
    static Character c1 = '海';
    static String s = "a";


    public static void main(String[] args) {

        if ((c >= 0x4e00)&&(c <= 0x9fbb)){
            System.out.println("中文");
        }
        System.out.println(0x4e00);
        System.out.println(i3==i4);
        System.out.println(i3.equals(i4));
        System.out.println((int)c+1);
        System.out.println(c1.hashCode());
        System.out.println(s.hashCode());


    }
}
