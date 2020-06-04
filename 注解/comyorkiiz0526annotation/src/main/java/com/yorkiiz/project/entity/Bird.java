package com.yorkiiz.project.entity;

import org.springframework.beans.factory.annotation.Value;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class Bird {

    @Value("鹦鹉")
    private String name;
    @Value("2")
    private int age;
   // @Value("2")
    @Value("${bird.color}")
    private  String color;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Bird{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }
}
