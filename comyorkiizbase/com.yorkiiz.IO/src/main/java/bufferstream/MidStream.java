package bufferstream;

import java.io.*;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class MidStream {

    public static void main(String[] args) throws IOException {
        File filei = new File("C:\\Users\\Administrator\\Downloads\\三菱PLC编程软件software_GX+Developer+8.86.rar");
        File fileo = new File("C:\\Users\\Administrator\\Downloads\\三菱PLC编程软件software_GX+Developer+8.862.rar");
        long now = System.currentTimeMillis();

        InputStream fis = new FileInputStream(filei);
        OutputStream fos = new FileOutputStream(fileo);
        int len;
        int index = 0;
        byte[] bytes = new byte[1024];
        while((len=fis.read(bytes))!=-1){
            fos.write(bytes);
            index++;
            if(index % (1024) == 0){
                System.out.println("打印了"+ index/1024 + "MB");
            }
        }
        fis.close();
        fos.close();

        System.out.println("打印完成"+"一共花费了"+(System.currentTimeMillis()-now)/1000+"秒");



    }


}
