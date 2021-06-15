package P5.S21;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/11/20-17:11
 */
public class ThreadDemo extends Thread{

    @Override
    public void run() {
        System.out.println("当前线程："+Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        ThreadDemo threadDemo=new ThreadDemo();
        threadDemo.start(); //启动一个线程
    }
}
