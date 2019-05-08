package com.yonyou.demo;

import static java.lang.Math.*;

public class StaticTest {
    public static void main(String[] args) {
        StaticBean staticBean = new StaticBean("1");
        StaticBean staticBean2 = new StaticBean("2");
        StaticBean staticBean3 = new StaticBean("3");
        StaticBean staticBean4 = new StaticBean("4");
        StaticBean.age = 33;
//        StaticBean{name='1'age=33}; StaticBean{name='2'age33} StaticBean{name='3'age33} StaticBean{name='4'age33}
        System.out.println(staticBean+"--" +staticBean2+"--" +staticBean3+ "--"+staticBean4);
        StaticBean.SayHello();//Hello i am java


        int max = max(1,2);
    }

}
