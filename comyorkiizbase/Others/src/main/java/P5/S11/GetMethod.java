package P5.S11;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class GetMethod {

    public static void main(String[] args) {

        String s1 = "xiaoming";
        String s2 = "xiaohong";
        String s3 = "xiaobai";

        List<String> arraylist = new ArrayList();
        List<String> linklist = new LinkedList();

        arraylist.add(s1);
        arraylist.add(s2);
        arraylist.add(s3);

        linklist.add(s1);
        linklist.add(s2);
        linklist.add(s3);

        String sa =  arraylist.get(1);
        String sl =  linklist.get(1);




    }

}
