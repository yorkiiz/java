package P5.S25.MyDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class CycleBarrierDemo {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Thread(()->{
            System.out.println("开始执行cyclicBarrier");
        }));

        new Thread(()->{
            try {
                System.out.println("thread1 start");
                cyclicBarrier.await();
                System.out.println("thread1 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                System.out.println("thread2 start");
                cyclicBarrier.await();
                System.out.println("thread2 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

    }

    

}
