package P5.S25;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/11/26-20:34
 */
public class LockDemo {
    static Lock lock= new ReentrantLock(); //重入锁
    public static int count=0;
    public static void incr(){ //递增
        try {
            lock.lock(); //获得锁 线程A
            Thread.sleep(1);
            decr();
            count++; //count++ (只会由一个线程来执行)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock(); //释放锁
        }
    }
    public static void decr(){ //递减
        lock.lock(); //有需要争抢锁 ，线程A （不需要争抢锁，记录重入次数即可）
        count--;
        lock.unlock();
    }
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(LockDemo::incr).start();
        }
        Thread.sleep(4000);
        System.out.println("result:"+count);
    }
}
