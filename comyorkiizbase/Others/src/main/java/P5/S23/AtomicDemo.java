package P5.S23;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/11/21-11:28
 */
public class AtomicDemo {

//    public static int count=0;
    private static AtomicInteger atomicInteger=new AtomicInteger(0);
    public static void incr(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        count++; //count++ (只会由一个线程来执行)
        atomicInteger.incrementAndGet();
    }
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(AtomicDemo::incr).start();
        }
        Thread.sleep(4000);
        System.out.println("result:"+atomicInteger.get());
    }
}
