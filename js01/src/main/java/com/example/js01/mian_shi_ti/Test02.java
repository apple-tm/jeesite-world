package com.example.js01.mian_shi_ti;

//静态变量和实例变量的区别？
public class Test02 {
    private static int i = 5;
    private int j = 2;

    public static void main(String[] args) {

        Test02 test02 = new Test02();
        test02.setJ(3);
        System.out.println(test02.getJ());
    }

    public static int getI() {
        return i;
    }

    public static void setI(int i) {
        Test02.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
