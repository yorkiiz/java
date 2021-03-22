package com.thread;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class TestThread extends Thread {

    private Thread thread;
    public TestThread(Thread thread){
        this.thread = thread;
    }


    @lombok.SneakyThrows
    public void run(){
        thread.join();
        for(int i=0;i<100;i++){
            System.out.println(currentThread()+"     i:"+i);
        }
    }

}
