package Mytest;

import java.util.Scanner;

public class YanghuiTringle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        System.out.println("打印n行的数组");
        int[][] arr = new int[num][];
        for(int i=0;i<num;i++){
            arr[i] = new int[i+1];
            arr[i][0]=1;
            arr[i][i]=1;
        }
        if(num>2){
            for(int i=2;i<num;i++){
                for(int j=1;j<i;j++){
                    arr[i][j]=arr[i-1][j-1]+arr[i-1][j];
                }
            }
        }
        for(int i=0;i<num;i++){
            for(int j=0;j<=i;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
}
