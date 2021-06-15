package P5.S24;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/11/26-14:59
 */
public class VolatileSyncDemo {

    private VolatileSyncDemo(){}

    private volatile static VolatileSyncDemo instance=null;
    //DCL问题

    public static VolatileSyncDemo getInstance(){
        if(instance==null){
            synchronized(VolatileSyncDemo.class) {
                if(instance==null) {
                    instance = new VolatileSyncDemo();
                }
            }
        }
        return instance;
    }

    /**
     * instance = new VolatileSyncDemo();
     * ->
     * 1. memory=allocate()
     * 2.
     * 3. instance=memory
     *    ctorInstance(memory)
     *
     * 1.3.2 (不完整实例）
     */
}
