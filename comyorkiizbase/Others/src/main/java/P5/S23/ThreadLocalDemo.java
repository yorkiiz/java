package P5.S23;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/11/21-16:05
 */
public class ThreadLocalDemo {

    private static Integer num=0;

    public static final ThreadLocal<Integer> local=new ThreadLocal<Integer>(){
        protected Integer initialValue(){
            return 0; //初始值
        }
    };
    public static final ThreadLocal<Integer> local1=new ThreadLocal<Integer>();
    public static void main(String[] args) {
        Thread[] threads=new Thread[5];
        //希望每个线程都拿到的是0
        for (int i = 0; i < 5; i++) {
            threads[i]=new Thread(()->{
//                num+=5;
                int num=local.get(); //拿到初始值
                local1.get();
                num+=5;
                local.set(num);
                System.out.println(Thread.currentThread().getName()+"->"+num);
            },"Thread-"+i);
        }
        for(Thread thread:threads){
            thread.start();
        }
    }
}
