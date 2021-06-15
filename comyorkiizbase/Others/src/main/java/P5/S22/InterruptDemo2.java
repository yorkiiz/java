package P5.S22;

import java.util.concurrent.TimeUnit;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/11/20-21:07
 */
public class InterruptDemo2 {

    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->{
            while(true){
                //true标识被中断过
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("before:"+Thread.currentThread().isInterrupted());
                    Thread.interrupted(); // 对中断标识复位 false
                    System.out.println("after:"+Thread.currentThread().isInterrupted());
                }
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt(); //中断
    }
}
