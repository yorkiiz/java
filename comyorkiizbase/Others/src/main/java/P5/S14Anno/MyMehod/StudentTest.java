package P5.S14Anno.MyMehod;

import java.lang.reflect.Field;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class StudentTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        String path = "P5.S4Anno.MyMehod.Student";
        String keys = getkeyFiled(path);
        System.out.println(keys);
    }

    public static String getkeyFiled(String path) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String keyvalue="";
        Class<?> clazz = Class.forName(path);
        Field[] fileds = clazz.getDeclaredFields();
        for (Field filed : fileds) {
            if(filed.isAnnotationPresent(KeyField.class)){
                //assert keyvalue != null;
                keyvalue = keyvalue + filed.getName();
            }
        }

        return keyvalue;
    }

}
