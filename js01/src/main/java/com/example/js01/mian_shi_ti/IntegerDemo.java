package com.example.js01.mian_shi_ti;

public class IntegerDemo {

    private static Integer[] arr = new Integer[2<<8];

    public Integer v;

    static {
        for (int i=-128; i<= 127; i++) {
            arr[i+128] = new Integer(i);
        }
    }

    public IntegerDemo(int i) {
        if (i >= -128 && i<=127) {
            v = arr[i+128];
        } else {
            v= new Integer(i);
        }
    }

    public static void main(String[] args) {
        IntegerDemo integerDemo1 = new IntegerDemo(162);
        IntegerDemo integerDemo2 = new IntegerDemo(162);
        System.out.println(integerDemo1.v==integerDemo2.v);
    }

}
