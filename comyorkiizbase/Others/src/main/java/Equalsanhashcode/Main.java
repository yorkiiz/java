package Equalsanhashcode;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

import java.util.Scanner;
public class Main{

    public static void main(String[] args){
        StringBuilder str=null;
        char A=' ',B= ' ';
        int count=0;
        System.out.println("String str = ");
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            str = new StringBuilder(sc.nextLine());
            A = sc.nextLine().charAt(0);
            B = sc.nextLine().charAt(0);
            break;
        }



        for(int i = 0; i<str.length(); i++){
            if(str.charAt(i)==A){
                str.setCharAt(i,B);
                count++;
            }
        }
        System.out.println("Result String:"+str);
        System.out.println("A count:"+count);


    }
}