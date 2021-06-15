package P5.S25;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/11/28-15:16
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        //当前可以获得的最大许可数量是5个
        //AQS   ->state
        Semaphore semaphore=new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            new Car(i,semaphore).start();
        }
    }
    static class Car extends Thread{
        private int num;
        private Semaphore semaphore;
        public Car(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }
        @Override
        public void run() {
            try {
                semaphore.acquire(); //获得一个许可,如果获取不到许可，就会被阻塞
                System.out.println("第"+num+" 占用一个停车位");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("第"+num+"  俩车走了");
                semaphore.release(); //释放许可
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
