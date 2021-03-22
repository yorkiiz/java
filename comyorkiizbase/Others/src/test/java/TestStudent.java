import statictest.Home;
import statictest.Student;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class TestStudent {

    public static void main(String[] args) {
        Student st1 = new Student();
        st1.setAge("18");
        st1.setName("xiaoming");
        Student st2 = new Student();
        st2.setAge("18");
        st2.setName("xiaoming");
        st2.setHome(new Home("hangzhou"));

        System.out.println(st1.equals(st2));
        System.out.println("st1的hashcode:"+st1.hashCode());
        System.out.println("st2的hashcode:"+st2.hashCode());
    }

}
