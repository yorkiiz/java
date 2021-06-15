package P5.S21;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/11/20-17:13
 */
public class RunnableDemo implements Runnable{
    @Override
    public void run() {
        System.out.println("当前线程："+Thread.currentThread().getName());
    }
    public static void main(String[] args) {
        RunnableDemo runnableDemo=new RunnableDemo();
        Thread thread=new Thread(runnableDemo);
        thread.start();//启动线程
    }
}
