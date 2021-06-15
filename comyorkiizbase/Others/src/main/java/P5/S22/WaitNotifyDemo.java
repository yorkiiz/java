package P5.S22;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/11/20-20:16
 */
public class WaitNotifyDemo {

    public static void main(String[] args) {
        Queue<String> queue=new LinkedList<>();
        int size=10;
        Producer producer=new Producer(queue,size);
        Consumer consumer=new Consumer(queue,size);
        Thread t1=new Thread(producer);
        Thread t2=new Thread(consumer);
        t1.start();
        t2.start();
    }
}
