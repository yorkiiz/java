package com.yorkiiz.domain;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleTest {

    public static void main(String[] args) {
        ScheduledExecutorService executorService4 = Executors.newScheduledThreadPool(1);

        executorService4.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("第一次submit");
            }
        });
        executorService4.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("第二次submit");
            }
        });
        executorService4.schedule(new Test(),1, TimeUnit.SECONDS);
        //executorService4.scheduleAtFixedRate(new Test(),1,2,TimeUnit.SECONDS);
        executorService4.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("execute");
            }

        });
}
}