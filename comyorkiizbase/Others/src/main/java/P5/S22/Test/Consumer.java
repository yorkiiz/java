package P5.S22.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class Consumer extends Thread{

    private Queue<String> bags;
    private Integer size;

    public Consumer(Queue<String> bags,Integer size){
        this.bags = bags;
        this.size = size;
    }

    @Override
    public void run() {
        while(true){
            synchronized (bags){
                while(bags.size()==0){
                    System.out.println("队列为空,size:"+bags.size());
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

                String bag = bags.remove();
                System.out.println("消费者消费消息:"+bag);
                bags.notify();
            }
        }
    }
}
