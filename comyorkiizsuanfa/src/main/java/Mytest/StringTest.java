package Mytest;

public class StringTest {
    public static void main(String[] args) {
        char[][] arr = new char[3][3];
        arr[0][0] = 'a';
        arr[1][1] = 'b';
        String s1 = String.valueOf(arr[0][0]);
        System.out.println(s1);
        String s2 = String.valueOf(arr[0]);
        System.out.println(s2);
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0][0]);

        String sss = "abcdefg";
        System.out.println(sss.substring(0,sss.length()));

    }
}
