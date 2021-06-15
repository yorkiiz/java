package exercise;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class ClassTest {

    String str = new String("hello");
    char[] ch = {'a','b','c'};
    public void fun(String str,char ch[]){
        str="world";
        ch[0]='d';
    }

    public static void main(String[] args){
        ClassTest test1 = new ClassTest();
        test1.fun(test1.str,test1.ch);
        System.out.println(test1.str+"and");
        System.out.println(test1.ch);

        int i = 1 << 30;
        System.out.println("i:"+i);

    }


}
