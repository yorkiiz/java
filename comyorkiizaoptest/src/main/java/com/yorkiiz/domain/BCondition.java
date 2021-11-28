package com.yorkiiz.domain;


public class BCondition implements Runnable {
    private DemoCondition condition;

    public BCondition(DemoCondition condition) {
        this.condition = condition;
    }

    public void run() {
        while(true){
            condition.b();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };
}