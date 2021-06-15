package P5.S14Anno;

import java.lang.reflect.Method;

@InvokAnno(className = "P5.S4Anno.Student2",methodName = "show")
public class MyMain {

    public static void main(String[] args) throws Exception {
        // 获取类对象
        Class<MyMain> clazz = MyMain.class;
        // 获取类对象中的注解
        InvokAnno an = clazz.getAnnotation(InvokAnno.class);
        /**
         *  注解本质是 接口  获取到的其实是接口的实现
         *  public class MyInvokAnno implements InvokAnno{
         *
         *      String className(){
         *          return "com.gupao.edu.anno2.Student1";
         *      }
         *      String methodName(){
         *          return "show";
         *      }
         *  }
         */
        // 获取注解中对应的属性
        String className = an.className();
        String methodName = an.methodName();
        System.out.println(className);
        System.out.println(methodName);

        // 通过反射的方式实现接口的功能
        Class<?> aClass = Class.forName(className);
        Method show = aClass.getDeclaredMethod("show");
        // 方法的执行
        Object o = aClass.newInstance();
        show.invoke(o); // 执行对应的方法
    }
}
