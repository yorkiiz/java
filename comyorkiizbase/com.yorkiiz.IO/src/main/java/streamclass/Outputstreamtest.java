package streamclass;

import java.io.*;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class Outputstreamtest {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Administrator\\Desktop\\out.txt");
        OutputStream out = new FileOutputStream(file);
        out.write(97);
        out.write(98);

        byte[] bytes = "章永杰".getBytes();
        out.write(bytes);
        out.close();


    }


}
