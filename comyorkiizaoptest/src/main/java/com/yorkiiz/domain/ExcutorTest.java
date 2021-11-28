package com.yorkiiz.domain;

import java.util.concurrent.*;

public class ExcutorTest {

    public static void main(String[] args) {


        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future f = executorService.submit(new Thread());
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        ExecutorService executorService3 = Executors.newCachedThreadPool();
        ;
        ExecutorService executorService4 = Executors.newScheduledThreadPool(1);
    }
}
