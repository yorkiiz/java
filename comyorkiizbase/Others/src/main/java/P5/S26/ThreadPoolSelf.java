package P5.S26;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/11/28-18:19
 */
public class ThreadPoolSelf extends ThreadPoolExecutor {

    public ThreadPoolSelf(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }

    //任务执行开始
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        //TODO 通过一个属性来记录任务的开始时间
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        //任务执行结束
        System.out.println("初始线程数:"+this.getPoolSize());
        System.out.println("核心线程数："+this.getCorePoolSize());
        System.out.println("正在执行的任务数量："+this.getActiveCount());
        System.out.println("已经执行的任务数："+this.getCompletedTaskCount());
        System.out.println("任务总数"+this.getTaskCount());
    }
}
