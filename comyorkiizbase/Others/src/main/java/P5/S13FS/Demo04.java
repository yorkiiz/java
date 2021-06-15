package P5.S13FS;

import java.lang.reflect.Field;

public class Demo04 {

    /**
     * Field操作
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Class<User> userClass = User.class;
        // 获取User对象
        User user = userClass.newInstance();
        // 获取类型中定义的字段 共有的字段以及父类中共有的字段
        //getModifiers:什么都不加 是0 ， public  是1 ，private 是 2 ，protected 是 4，static 是 8 ，final 是 16
        Field[] fields1 = userClass.getFields();
        for(Field f:fields1){
            System.out.println(f.getModifiers() + " " + f.getName());
        }
        System.out.println("--------------------");
        // 可以获取私有的字段  只能够获取当前类中
        Field[] fields2 = userClass.getDeclaredFields();
        for(Field f:fields2){
            System.out.println(f.getModifiers() + " " + f.getName());
        }

        // 获取name字段对应的Field
        Field nameField = userClass.getDeclaredField("name");
        // 如果要修改私有属性信息那么我们需要放开权限
        nameField.setAccessible(true);
        nameField.set(user,"咕泡");
        System.out.println(user.getName());
        // 如果对静态属性赋值
        Field addressField = userClass.getDeclaredField("address");
        addressField.set(null,"湖南长沙");
        System.out.println(User.address);

    }
}
