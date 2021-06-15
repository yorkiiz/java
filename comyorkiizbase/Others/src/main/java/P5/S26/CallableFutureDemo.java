package P5.S26;

import java.util.concurrent.*;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/11/28-22:49
 */
public class CallableFutureDemo implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("Hello Mic");
        Thread.sleep(3000); //睡眠3s
        return "Mic";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
       /* CallableFutureDemo callableFutureDemo=new CallableFutureDemo();
        FutureTask futureTask=new FutureTask(callableFutureDemo);
        new Thread(futureTask).start();
        //get方法是属于阻塞方法
        System.out.println(futureTask.get());*/

        ExecutorService executorService= Executors.newFixedThreadPool(1);
        CallableFutureDemo callableFutureDemo=new CallableFutureDemo();
        FutureTask future=(FutureTask) executorService.submit(callableFutureDemo);
        System.out.println(future.get());

    }
}
