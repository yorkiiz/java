package P5.S23;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/11/21-13:39
 */
public class SyncDemo {

    //对象锁(同一个对象有效)  this
    public synchronized void demo(){

    }
    public void demo1(){
        //TODO
        synchronized (this){  //对象锁
        }
        //TODO
    }
    //类级别的锁  SyncDemo.class
    public synchronized static void demo3(){

    }
    public void demo4(){
        //TODO
        synchronized (SyncDemo.class){

        }
    }


    public static void main(String[] args) {
        SyncDemo syncDemo=new SyncDemo();
        SyncDemo syncDemo1=new SyncDemo();
        //无法实现两个线程的互斥.
        //如果访问demo3的话，那么下面两个线程会存在互斥
        new Thread(()->{  //syncDemo这个实例
            syncDemo.demo1();
        }).start();

        new Thread(()->{ //BLOCKED状态
            syncDemo1.demo1();//syncDemo1这个实例
        }).start();
    }

}
