package exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class ArrayListCapality {

    public static void main(String[] args) {
        int lenth = 1024*1024*256+1024*1024*50;
        List a = new ArrayList(lenth);
        System.out.println(lenth);

        System.out.println(a.size());
        for(int i=0;i<lenth;i++){
            a.add(i);
        }

        for(int i=0;i<10;i++){
            a.add(i);
            System.out.println(a.size());
        }

    }


}
