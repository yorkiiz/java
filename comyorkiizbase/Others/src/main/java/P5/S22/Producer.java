package P5.S22;

import java.util.Queue;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/11/20-20:10
 */
public class Producer implements Runnable{

    private Queue<String> bags;
    private int size;

    public Producer(Queue<String> bags, int size) {
        this.bags = bags;
        this.size = size;
    }


    @Override
    public void run() {
        int i=0;
        while(true){
            i++;
            synchronized (bags){
                while(bags.size()==size){
                    System.out.println("bags已经满了");
                    //TODO? 阻塞？
                    try {
                        bags.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("生产者-生产:bag"+i);
                bags.add("bag"+i);
                //TODO? 唤醒处于阻塞状态下的消费者
                bags.notifyAll();
            }
        }
    }
}
