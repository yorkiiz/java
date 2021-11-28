package com.yorkiiz.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Driver {

    private String name;
    private int age;

    public void drive(){
        System.out.println("i am driving");
    }

    public void stop(){
        System.out.println("i cant drive");
    }
    Map a = new ConcurrentHashMap();
    Map b = new HashMap();

    ReentrantLock lock = new ReentrantLock();


}
