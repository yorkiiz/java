package statictest;

import java.util.Objects;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class Student {

    private String name;

    private Home home;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (home != null ? !home.equals(student.home) : student.home != null) return false;
        return age != null ? age.equals(student.age) : student.age == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (home != null ? home.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    private String age;


}
