package P5.S24;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/11/26-14:55
 */
public class StaticDemo {
    private StaticDemo(){}
    private static StaticDemo instance=new StaticDemo();
    public static StaticDemo getInstance(){
        return instance;
    }
}
