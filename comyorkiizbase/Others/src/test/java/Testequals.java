import org.junit.Test;
import statictest.Bird;
import statictest.Person;
import statictest.Student;

/**
 * @auther:
 * @date:
 * @describtion:
 **/


public class Testequals {

    @Test
    public void testequals(){

        Person a = new Person();
        a.setAge(10);
        a.setMoney(20);


        Person b = new Person();
        b.setAge(10);
        b.setMoney(20);

        Person c = new Person();
        c.setAge(20);
        c.setMoney(20);


        System.out.println("a"+a+"hashcode"+a.hashCode());
        System.out.println("b"+b+"hashcode"+b.hashCode());
        System.out.println("c"+c+"hashcode"+c.hashCode());


        System.out.println(a.equals(b));
        System.out.println(a.equals(c));


    }


    @Test
    public void testBird() throws CloneNotSupportedException {
        Bird a = new Bird();
        a.setName("cuckoo");

        Bird b = new Bird();
        b.setName("cuckoo");

        Bird c = (Bird) a.clone();

        System.out.println("a"+a+"  hashcode"+a.hashCode()+"   adress:"+System.identityHashCode(a));
        System.out.println("b"+b+"  hashcode"+b.hashCode()+"   adress:"+System.identityHashCode(b));
        System.out.println("c"+c+"  hashcode"+c.hashCode()+"   adress:"+System.identityHashCode(c));

        System.out.println("cuckoo:"+System.identityHashCode("cuckoo"));
        System.out.println("cuckoo:"+System.identityHashCode("cuckoo"));

        System.out.println(a.equals(b));

    }

}
