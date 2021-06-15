package P5.S25.MyDemo;

import java.util.concurrent.CountDownLatch;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class CountDownLauchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(3);

        Runnable target;
        Thread t1 = new Thread(()->{
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread1 ends");
        },"t1");

        Thread t2 = new Thread(()->{
            countDownLatch.countDown();
            System.out.println("t2" + "ends");
        });

        Thread t3 = new Thread(()->{
            countDownLatch.countDown();
            System.out.println("t3" + "ends");
        });
        Thread t4 = new Thread(()->{
            countDownLatch.countDown();
            System.out.println("t4" + "ends");
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        countDownLatch.await();
        System.out.println(t2.isAlive());;
        System.out.println("***********************");

    }

}
