package com.yorkiiz.domain;

public class Test implements Runnable {
    @Override
    public void run() {
        System.out.println("当前时间是"+System.currentTimeMillis());
    }
}
