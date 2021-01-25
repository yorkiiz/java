package streamclass;

import java.io.*;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class FileInputstreamtofile {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Administrator\\Desktop\\out.txt");
        InputStream in = new FileInputStream(file);
        int len ;
        byte[] bytes = new byte[10];
        StringBuffer sb = new StringBuffer();

        while ((len = in.read(bytes))!=-1){
            String str = new String(bytes);
            sb.append(str);

        }

        System.out.println(sb);
    }


}
