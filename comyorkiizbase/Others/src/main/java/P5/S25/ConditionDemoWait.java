package P5.S25;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/11/29-11:54
 */
public class ConditionDemoWait extends Thread{

    private Lock lock;
    private Condition condition;

    public ConditionDemoWait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    /**
     * synchronized(class){
     *     class.wait();
     * }
     */
    @Override
    public void run() {
        System.out.println("begin - ConditionDemoWait");
        try {
            lock.lock();//ThreadA获得了锁
            condition.await(); //阻塞
            System.out.println("end - ConditionDemoWait");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
