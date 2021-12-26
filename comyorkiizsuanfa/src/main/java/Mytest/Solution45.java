package Mytest;

public class Solution45 {

    public int jump(int[] nums) {
        int curindex = 0;
        int times = 0;
        int max = 0;
        int maxindex = 0;
        int length = nums.length;
        while(curindex<length-1){
            times++;
            if(curindex+1+nums[curindex]>length-1){
                break;
            }
            for(int i=curindex+1;i<curindex+1+nums[curindex];i++){
                if(max<i+nums[i]){
                    max=i+nums[i];
                    maxindex=i;
                }
            }
            curindex = maxindex;
        }
        return times;

    }

    public static void main(String[] args) {
        Solution45 solution45 = new Solution45();
        System.out.println(solution45.jump(new int[]{1,2,3}));
    }
}
