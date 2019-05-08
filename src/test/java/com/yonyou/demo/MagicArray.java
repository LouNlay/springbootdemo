package com.yonyou.demo;

import java.util.*;

/**
 * 小Q拥有一个只存储了整数的序列叫魔法序列。一开始序列为空，小Q会执行以下两种操作
 * add（x）：表示往序列中添加一个值为x的整数
 * get（y）：表示在第y次add操作后，取出序列中第k小的数，并将其输出，其中k初始时候为1，每执行一次get操作之后，k的值会+1.
 */
public class MagicArray {

    private int k = 1;
    private ArrayList<Integer> array = new ArrayList<>();

    public void add(int x){
        array.add(x);
    }

    public void addAll(List<Integer> list){
        array.addAll(list);
    }

    public int get(int y){

        List<Integer> minNum = array.subList(0, y);
        minNum.sort(((Integer x, Integer z) -> x > z? 1 : -1));//从小到大排序
        int result = minNum.get(k-1);
        k++;
        return result;
    }

    public static void main(String[] args) {

//        int[] ins = {3,1,-4,2,8,-1000,2};

        MagicArray ma = new MagicArray();
        ma.addAll(Arrays.asList(3,1,-4,2,8,-1000,2));

        System.out.println(ma.get(1));
        System.out.println(ma.get(2));
        System.out.println(ma.get(6));
        System.out.println(ma.get(6));


    }

}
