package P5.S32;

import java.io.*;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class SerializableDemo {

    public static void main(String[] args) throws IOException {
        Student st1 = new Student();
        st1.setAge("18");
        st1.setName("zhang");

        File file = new File("C:\\Users\\Administrator\\Desktop\\Student");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(st1);
        objectOutputStream.close();
    }

}
