import Entity.Student;

public class DemoHashcode {
    public static void main(String[] args) {
        Student s1 = new Student("xiaoming",18);
        Student s2 = new Student("xiaoming",18);
        Student s3 = new Student("xiaohong",18);
        Student s4 = s1;
        Student s5 = s1.clone();
        System.out.println("s1 hashcode:"+s1.hashCode());
        System.out.println("s2 hashcode:"+s2.hashCode());
        System.out.println("s3 hashcode:"+s3.hashCode());
        System.out.println("s4 hashcode:"+s4.hashCode());
        System.out.println("s5 hashcode:"+s5.hashCode());

        System.out.println(s1==s3);
        System.out.println(s1==s2);
        System.out.println(s1.equals(s3));
        System.out.println(s1.equals(s2));
        System.out.println(s4.equals(s1));
    }
}
