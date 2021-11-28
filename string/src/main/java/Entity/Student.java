package Entity;

import java.util.Objects;

public class Student {
    private String name;
    private Integer age;

    public Student(String name,Integer age){
        this.name = name;
        this.age = age;
    }



    public Student clone(){
        return this;
    }
}
