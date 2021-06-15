package P5.S13FS;

public class Demo03 {

    public static void main(String[] args) throws Exception {
        // 获取类对象的四种方式
        Class<User> clazz1 = User.class;
        Class<?> clazz2 = Class.forName("P5.S13FS.User");
        //Class<? extends User> clazz3 = new User().getClass();
        Class<?> clazz4 = Demo03.class.getClassLoader().loadClass("P5.S13FS.User");

        // 获取类的相关结构
        System.out.println(clazz1.getModifiers()); // 获取类的修饰符
        System.out.println(clazz1.getPackage());
        System.out.println(clazz1.getName());
        System.out.println(clazz1.getSuperclass());
        System.out.println(clazz1.getClassLoader());
        System.out.println(clazz1.getSimpleName());
        System.out.println(clazz1.getInterfaces().length); // 获取类似实现的所有的接口
        System.out.println(clazz1.getAnnotations().length);

    }
}
