package com.yonyou.demo;

public class ThreadDemo extends Thread {

    public static void main(String[] args) {
        ThreadDemo t1 = new ThreadDemo();
        t1.start();
        ThreadDemo t2 = new ThreadDemo();
        t2.start();
    }

    @Override
    public synchronized void start() {
        System.out.println("开始执行父类线程:"+this.getName()+"----"+this.getId());

        System.out.println(this.toString());

        super.start();
        System.out.println("执行线程结束:"+this.getName()+"----"+this.getId());
    }
}
