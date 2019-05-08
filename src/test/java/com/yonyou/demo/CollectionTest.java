package com.yonyou.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionTest {

    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<String>();
        arrayList.add("s");
        arrayList.add("e");
        arrayList.add("n");
        /**
         * ArrayList转数组
         */
        int size=arrayList.size();
        String[] a = arrayList.toArray(new String[size]);
        //输出第二个元素
        System.out.println(a[1]);//结果：e
//         排序
//        Arrays.sort(a);
        //输出整个数组
        System.out.println(Arrays.toString(a));//结果：[s, e, n]
        /**
         * 数组转list
         */
        List<String> list=Arrays.asList(a);
        /**
         * list转Arraylist
         */
        List<String> arrayList2 = new ArrayList<String>();
        arrayList2.addAll(list);
        //排序
//        Collections.sort(list);
        System.out.println(list);
    }

}
