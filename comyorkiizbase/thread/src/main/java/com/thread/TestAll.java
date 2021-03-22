package com.thread;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class TestAll {

    public static void main(String[] args) throws InterruptedException {

        Thread previous = Thread.currentThread();
        for(int i=0;i<10;i++){
            Thread t = new Thread(new TestThread(previous));

            t.start();
            previous = t;
        }
        Thread.currentThread().sleep(10000);
        System.out.println("**************************************");


    }

}
