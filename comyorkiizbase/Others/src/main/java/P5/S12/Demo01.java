package P5.S12;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Demo01 {
    public static void main(String[] args) throws Exception {
        // 指定能够存放的数据类型 统一类型防止出现 类型转换异常
        List<String> list = new ArrayList();
        list.add("1");
        list.add("hello");
        list.add("GUPAO");
        for (Object obj: list) {
            System.out.println((String)obj);
        }
        // 通过反射的方式添加数据到集合中
        Class<? extends List> aClass = list.getClass();
        Method method = aClass.getDeclaredMethod("add", Object.class);
        method.invoke(list,new Object());
        System.out.println(list);

    }
}
