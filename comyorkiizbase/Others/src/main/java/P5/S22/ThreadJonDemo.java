package P5.S22;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/11/20-19:25
 */
public class ThreadJonDemo {
    private static int x=0;
    private static int i=0;
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
            //阻塞操作
            i=1;
            x=2;
        });
        Thread t2=new Thread(()->{
            i=x+2;
        });
        //两个线程的执行顺序，
        t1.start();
        t1.join(); //t1线程的执行结果对于t2可见(t1线程一定要比t2线程有限执行) --- 阻塞
        t2.start();
        Thread.sleep(1000);
        System.out.println("result:"+i);

    }
    public  final synchronized void join()  {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
