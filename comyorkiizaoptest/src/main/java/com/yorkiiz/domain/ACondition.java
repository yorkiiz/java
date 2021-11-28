package com.yorkiiz.domain;


public class ACondition implements Runnable {
    private DemoCondition condition;
    public ACondition(DemoCondition condition) {
        this.condition=condition;
    }

    public void run() {
        while(true){
            condition.a();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}