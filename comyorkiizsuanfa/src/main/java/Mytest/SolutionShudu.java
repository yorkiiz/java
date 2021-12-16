package Mytest;

import java.util.Arrays;

public class SolutionShudu {
    public boolean isValidSudoku(char[][] board) {
        char[] arr = new char[9];
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                arr[j]=board[i][j];
            }
            if(checkbox(arr)==false){
                return false;
            }
        }

        for(int j=0;j<9;j++){
            for(int i=0;i<9;i++){
                arr[i]=board[i][j];
            }
            if(checkbox(arr)==false){
                return false;
            }
        }


        for(int i=1;i<9;i=i+3){
            for(int j=1;j<9;j=j+3){
                int index = 0;
                for(int p=i-1;p<i+2;p++){
                    for(int q=j-1;q<j+2;q++){
                        arr[index] = board[p][q];
                        index++;
                    }
                }
                if(checkbox(arr)==false){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean checkbox(char[] arr){
        Arrays.sort(arr);
        for(int i=0;i<arr.length-1;i++){
            if(arr[i]==arr[i+1]&&arr[i]!='.'){
                return false;
            }
        }
        return true;
    }
}
