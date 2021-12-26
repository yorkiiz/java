package Mytest;

class Solution42 {
    public int trap(int[] height) {
        int length = height.length;
        int[] leftmax = new int[length];
        int[] rightmax = new int[length];
        int ans = 0;
        if(length==0){
            return 0;
        }
        leftmax[0]=height[0];
        for(int i=1;i<length;i++){
            leftmax[i] =  Math.max(height[i],leftmax[i-1]);
        }

        rightmax[length-1]=height[length-1];
        for(int i=length-2;i>=0;i--){
            rightmax[i] = Math.max(height[i],rightmax[i+1]);
        }

        for(int i=0;i<length;i++){
            ans = ans + Math.min(leftmax[i],rightmax[i])-height[i];
        }

        return ans;
    }


    public static void main(String[] args) {
        Solution42 solution42 = new Solution42();
        System.out.println(solution42.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}