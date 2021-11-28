import java.util.Arrays;

public class DemoString {
    public static void main(String[] args) {
        char[] chars = {'1','2','3','4','5','6','7','8','9'};
        int[] ints = {1,2,3,4,5,6,7,8,9};
        byte[] bytes = new byte[5];
        bytes[0] = 'a';
        String s = new String();
        System.out.println("s = "+ s);
        String s2 = new String(chars);
        System.out.println("s2 = "+s2);
        String s3 = new String(chars,2,3);
        System.out.println("s3 = "+s3);
        String s4 = new String(ints,2,5);
        System.out.println("s4 = "+s4);
        String s5 = new String(bytes);
        System.out.println("s5 = "+s5);
       String s6 = "test for startwith";
        System.out.println("s6 start with test"+s6.startsWith("test"));
        System.out.println("s6 start with for"+s6.startsWith("fors",5));
        System.out.println("s6 index of f"+s6.indexOf('f'));
        System.out.println("s6 index of 3t"+s6.indexOf('t',3));
        System.out.println("s6 substring f+3 "+s6.substring(5,8));
        System.out.println("s6 replace blank to ,"+s6.replace(' ',','));
        String s7 = "test,for;split,xxx,x,x";
        System.out.println("s7 split result is "+ Arrays.toString(s7.split(",",3)));
        System.out.println("s7 split result is "+ Arrays.toString(s7.split(",")));
        String s8 = String.join("\\", s6, "is", "cool");
        System.out.println("s8 join is "+s8);
        String s9 = "test for to char";
        System.out.println("to char:"+Arrays.toString(s9.toCharArray()));

    }
}
