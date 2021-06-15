package P5.S13FS;

import java.lang.reflect.Constructor;

public class Demo06 {

    /**
     * 构造器的操作
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Class<User> userClass = User.class;
        // 获取所有的公有的构造器
        Constructor<?>[] constructors = userClass.getConstructors();
        for (Constructor c:constructors){
            System.out.println(c.getModifiers() + " " + c.getName() );
        }
        System.out.println("************************");
        // 获取所有的构造器
        Constructor<?>[] declaredConstructors = userClass.getDeclaredConstructors();
        for (Constructor c:declaredConstructors){
            System.out.println(c.getModifiers() + " " + c.getName() );
        }
        // 1.直接通过newInstance创建对象
        User user = userClass.newInstance();
        // 2.获取对应的Construcator对象获取实例
        Constructor<User> declaredConstructor = userClass.getDeclaredConstructor(String.class, String.class);
        // 私有的构造器调用需要放开权限
        declaredConstructor.setAccessible(true);
        System.out.println(declaredConstructor.newInstance("gupao","男"));


    }
}
