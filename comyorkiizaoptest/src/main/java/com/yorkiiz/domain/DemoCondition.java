package com.yorkiiz.domain;


import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DemoCondition {
    private int signal;
    private Lock lock=new ReentrantLock();
    private Condition a=lock.newCondition();
    private Condition b=lock.newCondition();
    private Condition c=lock.newCondition();

    public void a(){
        lock.lock();
        while(signal!=0){
            try {
                a.await();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("a"+signal);
        signal++;
        b.signal();
        lock.unlock();
    }
    public void b(){
        lock.lock();
        while(signal!=1){
            try {
                b.await();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("b"+signal);
        signal++;
        c.signal();
        lock.unlock();
    }
    public void c(){
        lock.lock();
        while(signal!=2){
            try {
                c.await();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("c"+signal);
        signal=0;
        a.signal();
        lock.unlock();
    }
    public static void main(String[] args) {
        DemoCondition condition=new DemoCondition();
        ACondition a=new ACondition(condition);
        BCondition b=new BCondition(condition);
        CCondition c=new CCondition(condition);
        new Thread(b).start();
        new Thread(a).start();
        new Thread(a).start();
        new Thread(b).start();
        new Thread(c).start();
        new Thread(a).start();
        new Thread(c).start();
        new Thread(a).start();
        new Thread(c).start();
        new Thread(c).start();





    }
}
