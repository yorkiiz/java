package P5.S22.Test;

import java.util.Queue;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class Producer extends Thread{

    private Queue<String> bags;
    private Integer size;

    public Producer(Queue<String> bags,Integer size){
        this.bags = bags;
        this.size = size;
    }

    @Override
    public void run() {
        int i = 0;
        while(true){
            //i++;
            synchronized (bags){
                i++;
                while(bags.size()>=size){
                    System.out.println("生产者消息已经满了,size"+bags.size());
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

                System.out.println("生产者生产消息:"+i);
                bags.add("bags"+i);
                bags.notifyAll();
            }

        }
    }
}
