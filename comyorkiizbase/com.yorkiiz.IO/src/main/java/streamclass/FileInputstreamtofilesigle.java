package streamclass;

import java.io.*;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class FileInputstreamtofilesigle {

    public static void main(String[] args) throws IOException {
        File filei = new File("C:\\Users\\Administrator\\Desktop\\out.txt");
        File fileo = new File("C:\\Users\\Administrator\\Desktop\\out2.txt");
        InputStream in = new FileInputStream(filei);
        OutputStream out = new FileOutputStream(fileo);
        int len ;
        byte[] bytes = new byte[10];
        byte b;
        StringBuffer sb = new StringBuffer();

        while ((len = in.read())!=-1){
            char text = (char) len;
            System.out.println(text);
            out.write(len);
        }


    }


}
