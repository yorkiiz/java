package Mytest;

import java.util.Stack;

public class StackTest {
    public static void main(String[] args) {
        Stack a = new Stack<Integer>();
        a.push(1);
        a.push(2);
        a.push(3);
        a.push(4);
        System.out.println(a);

        System.out.println(a.peek());
        System.out.println(a.peek());

        
    }
}
