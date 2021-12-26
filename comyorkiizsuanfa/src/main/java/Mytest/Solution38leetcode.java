package Mytest;

public class Solution38leetcode {
        public String countAndSay(int n) {
            StringBuilder temp = new StringBuilder("1");
            StringBuilder res = find(n,temp);
            return res.toString();
        }

        public StringBuilder find(int n,StringBuilder temp){
            if(n==1){
                return temp;
            }
            StringBuilder res = new StringBuilder();
            int length = temp.length();
            int count=1;
            int p=0;
            for(int i=0;i<length;i++){
                if(i+1>=length){
                    //res = res + count + temp.charAt(i);
                    res.append(count).append(temp.charAt(i));
                }else if(temp.charAt(i)==temp.charAt(i+1)){
                    count++;
                }else if(temp.charAt(i)!=temp.charAt(i+1)){
                    //res = res + count + temp.charAt(i);
                    res.append(count).append(temp.charAt(i));
                    count=1;
                }

            }
            return find(n-1,res);

        }


    public static void main(String[] args) {
        Solution38leetcode solution38leetcode = new Solution38leetcode();
        System.out.println(solution38leetcode.countAndSay(2));
    }
}
