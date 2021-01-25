package bufferstream;

import java.io.*;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class SlowStream {

    public static void main(String[] args) throws IOException {
        File filei = new File("C:\\Users\\Administrator\\Desktop\\咕泡首创挑战年薪60W互联网架构师(P6+)成长路径V10.0.png");
        File fileo = new File("C:\\Users\\Administrator\\Desktop\\咕泡首创挑战年薪60W互联网架构师(P6+)成长路径V10.0copy.png");
        long now = System.currentTimeMillis();

        InputStream fis = new FileInputStream(filei);
        OutputStream fos = new FileOutputStream(fileo);
        int len;
        int index = 0;
        while((len=fis.read())!=-1){
            fos.write(len);
            index++;
            if(index % (1024) == 0){
                System.out.println("打印了"+ index/1024 + "KB");
            }
        }
        fis.close();
        fos.close();

        System.out.println("打印完成"+"一共花费了"+(System.currentTimeMillis()-now)/1000+"秒");



    }


}
