package com.example.js01.mian_shi_ti;

import java.util.Random;

public class SortMethod {


    public static void main(String[] args){

        /*//冒泡排序
        int length=10;
        int[] arr1;
        long a;
        SortMethod test=new SortMethod();
        arr1=test.creatArr(length);
        a=System.currentTimeMillis();
        test.bubbleSort(arr1,length);
        test.outArr(arr1,length);*/

        SortMethod test1 = new SortMethod();
        int[] arr2 = {43,23,54,24,25,63,87,41,44,99,77,100};
        int i = test1.searchArrValueIndex(arr2,44,0,11);
        System.out.println(i);








//        test.quickSort(arr1,0,arr1.length-1);
        // System.out.println(arr1);

    }



    //随机创建一维数组方法
    public static int[] creatArr(int length){
        int[] arr=new int[length];
        Random rand=new Random();
        for(int i=0;i<length;i++){
            arr[i]=rand.nextInt(10*length);
        }
        return arr;
    }


    //冒泡排序
    public void bubbleSort(int[] arr,int length){
        int temp;
        for (int i = 0;i < length - 1;i++){
            for (int j = 0;j < length - i - 1;j++){
                if (arr[j] > arr[j+1]){
                    temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    //递归实现二分查找
    public int searchArrValueIndex(int[] intArr,int value,int first,int last){
        if (value < intArr[first] || value > intArr[last] || first > last){
            return -1;
        }
        int center = (first + last)/2;
        if (value < intArr[center]) {
            return searchArrValueIndex(intArr,value,first,center-1);

        }else if (value > intArr[center]){
            return searchArrValueIndex(intArr,value,center+1,last);
        }else {
            return center;
        }
    }





    /*
    插入排序
     */
    public void insertSort(int[] arr,int length){
        int len = arr.length;
        int preIndex, current;
        for (int i = 1; i < len; i++) {
            preIndex = i - 1;
            current = arr[i];
            while(preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex+1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex+1] = current;
        }
    }

    /*
    打印数组
     */
    public void outArr(int[] arr,int length){
        for (int i=0;i<length;i++)
            System.out.println(arr[i]);
    }










    /*public static void main(String[] args) {
        SortMethod sortMethod = new SortMethod();
        int[] arr = sortMethod.creatArr(10);
        for (int i = 0;i < arr.length;i++){
            System.out.println(arr[i]);
        }

    }*/
}
