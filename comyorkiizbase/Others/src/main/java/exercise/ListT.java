package exercise;

import java.sql.Time;
import java.util.*;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class ListT {

    public static void main(String[] args) {
        int a = 1;
        Object obj = new Object();
        Integer integer = new Integer(1);
        Integer b = new Integer(2);
        String c = new String("yongjie");
        StringBuilder d = new StringBuilder("xiaoming");
        StringBuffer e = new StringBuffer("xiaohong");

        List lista = new ArrayList(1000000);
        List listb = new LinkedList();

        Map hashMap = new HashMap();

        Long timeMillisa = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            lista.add(integer);
        }
        System.out.println(System.currentTimeMillis()-timeMillisa);

        Long timeMillisb = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            listb.add(integer);
        }
        System.out.println(System.currentTimeMillis()-timeMillisb);


    }


}
