package P5.S32;

import java.io.Serializable;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class Student implements Serializable {

    public String name;

    public String age;

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
