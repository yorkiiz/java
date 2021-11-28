package com.yorkiiz.domain;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
    public static void main(String[] args) {
        // 创建一个线程池对象，控制要创建几个线程对象。
        // public static ExecutorService newFixedThreadPool(int nThreads)
        ExecutorService pool = Executors.newFixedThreadPool(2);

        // 可以执行Runnable对象或者Callable对象代表的线程
        pool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("第一次");
            }
        });
        pool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("第二次");
            }
        });
        pool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("第二次");
            }
        });
        //结束线程池
        pool.shutdown();
    }
}
