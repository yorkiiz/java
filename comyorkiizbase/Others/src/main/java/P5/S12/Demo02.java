package P5.S12;

import java.util.ArrayList;
import java.util.List;

public class Demo02 {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("gupao");
        list1.add("bobo");
        list1.add("mic");
        loop(list1);
    }

    public static void loop(List<?> list){
        for (int i = 0; i < list.size() ; i++) {
            System.out.println(list.get(i));
        }
    }
}
