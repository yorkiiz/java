package P5.S12;

public class Demo07 <K,V> {

    /**
     * 普通方法 可以使用 类中定义的泛型
     * @param k
     * @param v
     * @return
     */
    public K method1(K k,V v){
        return (K)null;
    }

    /**
     * 普通方法  使用方法中定义的泛型
     * @param t
     * @param v
     * @param <T>
     * @return
     */
    public <T> T method2(T t,V v){
        return (T)null;
    }

    /**
     * 在静态方法中我们没法使用 类中定义的泛型
     * @return
     */
    public static <K> K method3(){
        return null;
    }
}
