package P5.S25;

import java.util.concurrent.CountDownLatch;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/11/28-13:39
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        //state存储
        CountDownLatch countDownLatch=new CountDownLatch(3);

        new Thread(()->{
            countDownLatch.countDown(); //倒计时 3-1=2
            //修改state=state-1 通过cas设置到state这个字段上
        }).start();
        new Thread(()->{
            countDownLatch.countDown(); //倒计时 2-1=1
        }).start();
        new Thread(()->{
            countDownLatch.countDown(); //倒计时 1-1=0 ->触发唤醒操作
        }).start();

        countDownLatch.await(); //阻塞主线程
        System.out.println("线程执行完毕");
    }
}
