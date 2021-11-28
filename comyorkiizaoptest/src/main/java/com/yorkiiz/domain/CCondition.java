package com.yorkiiz.domain;


public class CCondition implements Runnable {
    private DemoCondition condition;

    public CCondition(DemoCondition condition) {
        this.condition = condition;
    }

    public void run() {
        while(true){
            condition.c();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
