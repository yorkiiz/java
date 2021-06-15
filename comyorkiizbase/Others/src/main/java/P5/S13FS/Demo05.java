package P5.S13FS;

import java.lang.reflect.Method;

public class Demo05 {

    public static void main(String[] args) throws Exception {
        User user = new User();
        Class<User> userClass = User.class;
        // 可以获取当前类及其父类中的所有的共有的方法
        Method[] methods = userClass.getMethods();
        for (Method m : methods) {
            System.out.println(m.getModifiers() + " " + m.getName());
        }
        System.out.println("**********");
        // 获取本类中的所有的方法 包括私有的
        Method[] declaredMethods = userClass.getDeclaredMethods();
        for (Method m:declaredMethods){
            System.out.println(m.getModifiers() + " " + m.getName());
        }
        Method jumpMethod = userClass.getDeclaredMethod("jump");
        // 放开私有方法的调用
        jumpMethod.setAccessible(true);
        jumpMethod.invoke(user);
        Method sayMethod = userClass.getDeclaredMethod("say", String.class);
        // 静态方法调用
        sayMethod.invoke(null,"咕泡666");
    }
}
