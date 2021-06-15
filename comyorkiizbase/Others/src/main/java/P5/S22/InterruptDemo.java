package P5.S22;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/11/20-20:55
 */
public class InterruptDemo {

    private static int i;

    //interrupt(jvm)
    //while
    //线程处于阻塞状态下的情况下(中断才有意义）
    //thread.join
    //wait
    //Thread.sleep


    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->{
            //Thread.currentThread().isInterrupted() 默认是false
           //正常的任务处理..
            try {
                System.out.println("Thread start");
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                //抛出异常来相应客户端的中断请求
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println("main thread start");
        Thread.sleep(5000);
        //interrupt 这个属性由false-true
        thread.interrupt(); //中断（友好）
    }
}
