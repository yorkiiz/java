package fileclass;

import java.io.*;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class Luanma {

    public static void main(String[] args) throws IOException {
        File filei = new File("C:\\Users\\Administrator\\Desktop\\新建文本文档.txt");
        BufferedReader bfr = new BufferedReader(new FileReader(filei));
        BufferedReader bfr2t = new BufferedReader(new InputStreamReader(new FileInputStream(filei)));
        String line;
        while((line=bfr.readLine())!=null){
            System.out.println(line);
        }

        bfr.close();


    }


}
