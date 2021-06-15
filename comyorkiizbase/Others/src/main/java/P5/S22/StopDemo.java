package P5.S22;

import java.util.concurrent.TimeUnit;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/11/20-20:49
 */
public class StopDemo {

    static volatile boolean stop=false;

    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new StopThread());
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        stop=true;
        t1.stop();//  kill -9
    }
    static class StopThread implements Runnable{
        @Override
        public void run() {
            while(!stop){
                System.out.println("持续运行");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
