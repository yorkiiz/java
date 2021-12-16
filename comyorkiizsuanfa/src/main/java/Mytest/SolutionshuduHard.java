package Mytest;

import java.util.ArrayList;
import java.util.List;

public class SolutionshuduHard {
    boolean flag = false;
    public void solveSudoku(char[][] board) {
        boolean[][] line = new boolean[9][10];
        boolean[][] row = new boolean[9][10];
        boolean[][][] block = new boolean[3][3][10];
        List<int[]> spaces = new ArrayList<int[]>();

        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j]!='.'){
                    int digit = board[i][j]-'0';
                    line[i][digit]=true;
                    row[j][digit]=true;
                    block[i/3][j/3][digit]=true;
                }else {
                    spaces.add(new int[]{i,j});
                }
            }
        }

        dfs(board,line,row,block,0,spaces);

        return ;
    }

    public void dfs(char[][] board,boolean[][] line,boolean[][] row,boolean[][][] block,int index,List<int[]> spaces){
        if(spaces.size()==index){
            flag = true;
            return ;
        }
        int i=spaces.get(index)[0];
        int j=spaces.get(index)[1];
        for(int k=1;k<10;k++){
            if(line[i][k]==false&&row[j][k]==false&&block[i/3][j/3][k]==false&&flag==false){
                board[i][j]=(char)('0'+k);
                line[i][k]=true;
                row[j][k]=true;
                block[i/3][j/3][k]=true;
                dfs(board,line,row,block,index+1,spaces);
                if(flag==false){
                    board[i][j]='.';
                    line[i][k]=false;
                    row[j][k]=false;
                    block[i/3][j/3][k]=false;
                }
            }
        }
    }
}
