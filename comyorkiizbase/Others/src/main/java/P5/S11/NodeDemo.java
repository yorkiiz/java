package P5.S11;


import java.util.LinkedList;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class NodeDemo {

    public static void main(String[] args) {
        Node<Integer> a = null;
        Node<Integer> b = null;
        Node<Integer> c = null;

        a.item=1;
        b.item=2;
        c.item=3;

        a.next=b;
        b.next=c;

        LinkedList<String> linkedList = new LinkedList<>();

    }

}
