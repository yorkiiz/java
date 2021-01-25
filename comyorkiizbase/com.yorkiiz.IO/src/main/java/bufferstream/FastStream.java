package bufferstream;

import java.io.*;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class FastStream {

    public static void main(String[] args) throws IOException {
        File filei = new File("D:\\[阳光电影www.ygdy8.net].解忧杂货店.HD.720p.国粤双语中字.mkv");
        File fileo = new File("C:\\Users\\Administrator\\Downloads\\[阳光电影www.ygdy8.net].解忧杂货店.HD.720p.国粤双语中字.mkv");
        long now = System.currentTimeMillis();

        InputStream fis = new BufferedInputStream( new FileInputStream(filei));
        OutputStream fos = new BufferedOutputStream(new FileOutputStream(fileo));
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
