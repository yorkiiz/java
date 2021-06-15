package P5.S25;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/11/29-11:54
 */
public class CondtionDemo {

    public static void main(String[] args) {
        Lock lock=new ReentrantLock();
        Condition condition=lock.newCondition();
        ConditionDemoWait conditionDemoWait=new ConditionDemoWait(lock,condition);
        ConditionDemoNotify conditionDemoNotify=new ConditionDemoNotify(lock,condition);
        conditionDemoWait.start();
        conditionDemoNotify.start();

    }
}
