package P5.S13FS;


import java.lang.reflect.Method;


public class Demo01 {

    /**
     * new User();
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 获取一个类对象
        Class<User> clazz = User.class;
        // 获取类对象对应的属性或者方法
        System.out.println(clazz.getName());
        System.out.println(clazz.getPackage());
        System.out.println(clazz.getClassLoader());
        System.out.println(clazz.getSuperclass());
        User user = clazz.newInstance();// 获取一个实例对象
        // 获取类型中的方法
        Method method = clazz.getDeclaredMethod("jump");
        // 通过反射执行方法
        method.setAccessible(true);
        method.invoke(user);


    }
}
