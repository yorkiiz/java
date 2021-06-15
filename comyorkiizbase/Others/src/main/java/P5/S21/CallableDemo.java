package P5.S21;

import java.util.concurrent.*;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/11/20-17:14
 */
public class CallableDemo implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("当前线程："+Thread.currentThread().getName());
        Thread.sleep(10000);
        return "Hello Mic";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService= Executors.newFixedThreadPool(1);
        Future<String> future=executorService.submit(new CallableDemo());
        //future.get 是一个阻塞方法
        System.out.println(Thread.currentThread().getName()+"-"+future.get());
    }
}
