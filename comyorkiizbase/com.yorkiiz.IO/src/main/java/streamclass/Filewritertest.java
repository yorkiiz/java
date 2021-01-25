package streamclass;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class Filewritertest {

    public static void main(String[] args) throws IOException {
        Writer wt = new FileWriter("C:\\Users\\Administrator\\Desktop\\out3.txt");

        //1.输入字节
        wt.write(97);
        wt.write('a');

        //2.输入字节数组
        char[] text = {'c','d'};
        wt.write(text);

        //3.输入字符串
        String sb = "国泰家圆";
        wt.write(sb);

        //4.必须要关闭输出流
        wt.close();

        System.out.println("write success");

    }

}
