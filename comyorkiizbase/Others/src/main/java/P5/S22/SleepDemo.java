package P5.S22;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/11/20-19:56
 */
public class SleepDemo extends Thread{
    public static void main(String[] args) {
        new SleepDemo().start();
    }
    @Override
    public void run() {
        System.out.println("begin:"+System.currentTimeMillis());
        try {
            Thread.sleep(3000); //睡眠3秒
            System.out.println("end:"+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Thread.yield()

    }
}
