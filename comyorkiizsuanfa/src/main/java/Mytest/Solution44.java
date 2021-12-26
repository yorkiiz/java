package Mytest;

public class Solution44 {
    public boolean isMatch(String s, String p) {
        int slength = s.length();
        int plength = p.length();
        boolean[][] res = new boolean[slength+1][plength+1];
        res[0][0] = true;

        for(int i=1;i<plength+1;i++){
            if(p.charAt(i-1)=='*'){
                res[0][i] = res[0][i-1];
            }
        }

        for(int i=1;i<slength+1;i++){
            res[i][0]=false;
        }

        for(int i=1;i<slength+1;i++){
            for(int j=1;j<plength+1;j++){
                if(p.charAt(j-1)=='*'){
                    res[i][j]=res[i-1][j]||res[i][j-1];
                }else if(p.charAt(j-1)=='?'||p.charAt(j-1)==s.charAt(i-1)){
                    res[i][j]=res[i-1][j-1];
                }
            }
        }

        return res[slength][plength];

    }

    public static void main(String[] args) {
        Solution44 solution44 = new Solution44();
        System.out.println(solution44.isMatch("adceb","*a*b"));
    }
}
